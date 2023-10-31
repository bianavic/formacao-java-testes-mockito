package org.edu.fabs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ServicoEnvioEmailTest {

    // mocka a  plataforma e injeta na classe de testes
    @Mock
    private PlataformaDeEnvio plataformaDeEnvio;
    @InjectMocks // injeta na classe de testes q queremos testar
    private ServicoEnvioEmail servicoEnvioEmail;

    // classe q consegue capturar argumentos de uma classe
    @Captor
    private ArgumentCaptor<Email> captor;

    @Test
    void validaDadosEnviadosParaAPlataforma() {

        String enderecoEmail = "email@email.com";
        String mensagem = "ola mundo teste mensagem";
        boolean ehFormatoHtml = false;

        servicoEnvioEmail.enviaEmail(enderecoEmail, mensagem, ehFormatoHtml);

        // capturar infos e recolher parametros, o argumento q esta sendo capturado
        Mockito.verify(plataformaDeEnvio).enviaEmail(captor.capture());

        Email emailCapturado = captor.getValue();

        assertEquals(enderecoEmail, emailCapturado.getEnderecoEmail());
        assertEquals(mensagem, emailCapturado.getMensagem());
        assertEquals(Formato.TEXTO, emailCapturado.getFormato());
    }

}