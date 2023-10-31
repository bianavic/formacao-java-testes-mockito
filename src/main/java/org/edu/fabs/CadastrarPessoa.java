package org.edu.fabs;

import java.time.LocalDate;

public class CadastrarPessoa {

    private ApiDosCorreios apiDosCorreios;

    // a api dos correios existe dentro de CadastrarPessoa
    // por isso temos q injetar o mock -> passar o mock para essa classe
    public CadastrarPessoa(final ApiDosCorreios apiDosCorreios) {
        this.apiDosCorreios = apiDosCorreios;
    }

    // chega no metodo cadastrarPessoa, constroi a referencia da classe pessoa,
    // entao buscaDadosComBaseNoCep com base no cep
    public Pessoa cadastrarPessoa(String nome, String documento, LocalDate nascimento, String cep) {
        Pessoa pessoa = new Pessoa(nome, documento, nascimento);
        DadosLocalizacao dadosLocalizacao = apiDosCorreios.buscaDadosComBaseNoCep(cep);
        pessoa.adicionaDadosDeEndereco(dadosLocalizacao);
        return pessoa;
    }

}
