package it.pc.test.WebSpringApp.dto.grid;

import lombok.Data;

@Data
public class SearchCriteria {
    private String keyColumn; // Campo ricerca griglia (colonna DB)
    private GridRequest.SearchOperations operation; // =, >, >=, etc...
    private Object value; // valore da ricercare
}
