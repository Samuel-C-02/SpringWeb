package it.pc.test.WebSpringApp.enums;

import java.util.HashMap;
import java.util.Map;

public enum Provenienza implements IEnum<Provenienza> {

    OTHER(0),
    USA(1),
    EU(2),
    CHINA(3),
    RU(4),
    UK(5);


    Provenienza(Integer id) {
        this.id = id;
    }

    Integer id;

    public static Provenienza getEnumByValue(int value) {
        return IEnum.getEnumByValueStatic(Provenienza.class, value);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
