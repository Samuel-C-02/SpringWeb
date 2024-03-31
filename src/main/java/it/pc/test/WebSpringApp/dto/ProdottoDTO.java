package it.pc.test.WebSpringApp.dto;

import it.pc.test.WebSpringApp.entity.TipoProdottoEntity;
import it.pc.test.WebSpringApp.enums.Provenienza;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdottoDTO {

    private Integer id;
    private String nomeProdotto;
    private BigDecimal costo;
    private boolean disponibile;
    private Provenienza provenienza;
    private TipoProdottoEntity tipoProdotto;
    private Integer produttoreId;

}
