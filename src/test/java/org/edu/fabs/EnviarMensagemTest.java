package org.edu.fabs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EnviarMensagemTest {

    @Spy
    private EnviarMensagem enviarMensagem;

    @Test
    void verificarComportamentoDaClasse() {
        // verifique se não houve interacao com o objeto
        Mockito.verifyNoInteractions(enviarMensagem);

        Mensagem mensagem = new Mensagem("Hello, Stranger!");

        // interacao
        enviarMensagem.adicionarMensagem(mensagem);

        // verificar se houve interacao
        Mockito.verify(enviarMensagem).adicionarMensagem(mensagem);

        // junit => nao houve a referencia de passar a mensagem, o STUBBING
        assertFalse(enviarMensagem.getMensagens().isEmpty());

    }

}