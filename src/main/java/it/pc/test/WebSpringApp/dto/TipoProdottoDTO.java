package it.pc.test.WebSpringApp.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TipoProdottoDTO extends AbstractBaseDTO<Integer> {

    private Integer idTipo;
    private String tipoProdotto;

}
