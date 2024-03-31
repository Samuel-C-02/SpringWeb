package it.pc.test.WebSpringApp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SedeDTO extends AbstractBaseDTO<Integer> {


    private Integer id;
    private String nomeSede;
    private String indirizzoSede;
    private BigDecimal fatturatoMedioAnnuale;

}
