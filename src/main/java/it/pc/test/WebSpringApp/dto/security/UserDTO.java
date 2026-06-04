package it.pc.test.WebSpringApp.dto.security;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends AbstractBaseDTO<Integer> {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private RoleDTO role;

}
