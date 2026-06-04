package it.pc.test.WebSpringApp.dto.grid;

import lombok.Data;

import java.util.List;

@Data
public class GridRequest {
    private int pageNumber;
    private int pageSize;
    private List<SearchCriteria> filters;
    private List<SortCriteria> sorts;

    public static enum SearchOperations {
        EQUALS, NOT_EQUALS, GREATER_THAN, LESS_THAN, LIKE, IN
    }

}
