package it.pc.test.WebSpringApp.dto;

import it.pc.test.WebSpringApp.entity.PartnerEntity;
import it.pc.test.WebSpringApp.enums.TipoCategoria;
import lombok.Data;

@Data
public class ProduttoreDTO {

    private Integer id;

    private String nome;

    private String descrizione;

    private PartnerEntity partner;

    private TipoCategoria categoria;

}
