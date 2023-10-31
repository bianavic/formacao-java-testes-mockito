package org.edu.fabs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CadastrarPessoaTest {

    // mockando a api
    @Mock
    private ApiDosCorreios apiDosCorreios;

    // mockar objeto, classe
    // a api dos correios existe dentro de CadastrarPessoa
    // por isso temos q INJETAR o MOCK -> passar o mock da api para essa classe
    @InjectMocks
    private CadastrarPessoa cadastrarPessoa; // verificar classe CadastrarPessoa

    @Test
    void validarDadosDeCadastro() {

        // chega no metodo cadastrarPessoa, constroi a REFERENCIA da classe pessoa,
        // entao buscaDadosComBaseNoCep com base no cep
        // nao quero q a chamada seja feita para a api (Mockito when, thenReturn)
        // RETORNA é o dado DadosLocalizacao
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("sp", "sao paulo", "avenida", "111", "bairro");

        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("1234")).thenReturn(dadosLocalizacao);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("maria", "1234", LocalDate.now(), "1234");

        // o nome passado foi maria, ENTAO o objeto precisa ter esse nome
        assertEquals("maria", pessoa.getNome());
        assertEquals("1234", pessoa.getDocumento());
        // e testar se foi pega a info da api fake
        assertEquals("sp", pessoa.getEndereco().getUf());
        assertEquals("111", pessoa.getEndereco().getComplemento());
    }

    @Test
    void cadastrarPessoa() {
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("sp", "sao paulo", "avenida", "111", "bairro");

        // MATCHERS -> ANY STRING
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenReturn(dadosLocalizacao);

        Pessoa pessoa = cadastrarPessoa.cadastrarPessoa("maria", "1234", LocalDate.now(), "1234");

        assertEquals("maria", pessoa.getNome());
        assertEquals("1234", pessoa.getDocumento());
        assertEquals("sp", pessoa.getEndereco().getUf());
        assertEquals("111", pessoa.getEndereco().getComplemento());
    }

    @Test
    void lancarExcecaoQuandoChamarApiDosCorreios() {

        // MATCHERS -> ANY STRING
//        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep(anyString())).thenThrow(IllegalArgumentException.class);

        // OU
        Mockito.doThrow(IllegalArgumentException.class)
                        .when(apiDosCorreios)
                .buscaDadosComBaseNoCep(anyString());

        assertThrows(IllegalArgumentException.class, () -> cadastrarPessoa.cadastrarPessoa("maria", "1234", LocalDate.now(), "1234"));
    }

}

// metodo 2 - declarando o mock
/*
class CadastrarPessoaTest2 {

    // mockando a api
    @BeforeAll
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    private ApiDosCorreios apiDosCorreios = Mockito.mock(ApiDosCorreios.class);

    @Test
    void cadastrarPessoa() {
    }

}*/
