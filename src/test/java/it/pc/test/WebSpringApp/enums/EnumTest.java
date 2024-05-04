package it.pc.test.WebSpringApp.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumTest {

    @Test
    void getEnumByValueTest() {
        TipoCategoria shouldBeVenditore = TipoCategoria.getEnumByValue(2);
        Assertions.assertEquals(TipoCategoria.VENDITORE, shouldBeVenditore);
    }

}
