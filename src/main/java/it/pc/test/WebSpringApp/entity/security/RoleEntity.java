package it.pc.test.WebSpringApp.entity.security;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.springframework.security.core.GrantedAuthority;

@Immutable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "role")
  private String role;

  @Override
  public String getAuthority() {
    return role;
  }
}
