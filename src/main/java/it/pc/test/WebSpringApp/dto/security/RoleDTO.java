package it.pc.test.WebSpringApp.dto.security;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDTO {
    private Integer id;
    private String role;
}
