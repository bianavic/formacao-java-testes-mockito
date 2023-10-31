import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ListaTest {

    // mocka a lista para mim
    // criei um objeto

    // stubbing => dizer o q é para ele fazer
    private List<String> letras;

    @Test
    void adicionarItemNaLista() {
        // nada foi colocado na lista, simplesmente mockou e vai surgir uma informacao
        // to fazendo o que quero com o objeto
        Mockito.when(letras.get(0)).thenReturn("B");

        //
        Assertions.assertEquals("B", letras.get(0));
    }

}
