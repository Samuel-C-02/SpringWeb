package it.pc.test.WebSpringApp.dto;

import lombok.Data;

@Data
public class AbstractBaseDTO<IdType> {
    private IdType id;
}
