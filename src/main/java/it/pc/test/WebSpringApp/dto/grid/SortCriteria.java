package it.pc.test.WebSpringApp.dto.grid;

import lombok.Data;

@Data
public class SortCriteria {
    private String keyColumn;
    private boolean desc;
}
