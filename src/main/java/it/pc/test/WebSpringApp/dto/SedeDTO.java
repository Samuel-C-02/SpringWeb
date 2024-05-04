package it.pc.test.WebSpringApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SedeDTO extends AbstractBaseDTO<Integer> {


    private Integer id;
    private String indirizzo;
    private String nazione;
    private LocalDate dataCostruzione;
    private Double valoreImmobile;

    public static SedeDTO of(Integer id, String indirizzo, String nazione, LocalDate dataCostruzione, Double valoreImmobile) {
        return new SedeDTO(id, indirizzo, nazione, dataCostruzione, valoreImmobile);
    }

    public static SedeDTO ofMinimal(Integer id, String indirizzo, String nazione) {
        return new SedeDTO(id, indirizzo, nazione, null, null);
    }

}
