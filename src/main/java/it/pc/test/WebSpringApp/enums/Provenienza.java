package it.pc.test.WebSpringApp.enums;

public enum Provenienza {

    USA(1),
    EU(2),
    CHINA(3),
    RU(4),
    OTHER(5);

    Provenienza(Integer id) {
        this.id = id;
    }

    Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
