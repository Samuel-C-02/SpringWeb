package it.pc.test.WebSpringApp.dto;

import it.pc.test.WebSpringApp.entity.TipoProdottoEntity;
import it.pc.test.WebSpringApp.enums.Provenienza;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProdottoDTO extends AbstractBaseDTO<Integer> {

    private Integer id;
    private String nomeProdotto;
    private BigDecimal costo;
    private boolean disponibile;
    private Provenienza provenienza;
    private TipoProdottoEntity tipoProdotto;
    private Integer produttoreId;

}
