package it.pc.test.WebSpringApp.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SedeDTO extends AbstractBaseDTO<Integer> {


    private Integer id;
    private String indirizzo;
    private String nazione;
    private LocalDate dataCostruzione;
    private Double valoreImmobile;


}
