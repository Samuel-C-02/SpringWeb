package it.pc.test.WebSpringApp.enums;

public enum TipoCategoria implements IEnum<TipoCategoria> {
    NON_SPECIFICATO(0),
    PRODUTTORE_VENDITORE(1),
    VENDITORE(2),
    PRODUTTORE(3),
    EXPORT_IMPORT(4);

    TipoCategoria(Integer id) {
        this.id = id;
    }

    Integer id;


    public static TipoCategoria getEnumByValue(int value) {
        return IEnum.getEnumByValueStatic(TipoCategoria.class, value);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
