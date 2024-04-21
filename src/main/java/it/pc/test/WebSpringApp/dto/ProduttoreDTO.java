package it.pc.test.WebSpringApp.dto;

import it.pc.test.WebSpringApp.entity.PartnerEntity;
import it.pc.test.WebSpringApp.entity.SedeEntity;
import it.pc.test.WebSpringApp.enums.TipoCategoria;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProduttoreDTO extends AbstractBaseDTO<Integer> {

    private Integer id;

    private String nome;

    private String descrizione;

    private PartnerDTO partner;

    private TipoCategoria categoria;
    private Double numeroDipendenti;
    private Double fatturatoAnnuo;
    private SedeDTO sede;
    private List<ProdottoDTO> prodotti;

}
