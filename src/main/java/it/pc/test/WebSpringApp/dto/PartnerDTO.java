package it.pc.test.WebSpringApp.dto;

import it.pc.test.WebSpringApp.entity.SedeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class PartnerDTO extends AbstractBaseDTO<Integer> {

    private Integer id;
    private String nome;
    private SedeEntity sede;
    private LocalDate dataFondazione;

}
