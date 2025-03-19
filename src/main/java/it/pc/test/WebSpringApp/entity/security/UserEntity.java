package it.pc.test.WebSpringApp.entity.security;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.entity.AbstractAuditEntity;
import it.pc.test.WebSpringApp.entity.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utente")
public class UserEntity extends AbstractBaseEntity<Integer> implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "utenti_id_gen")
    @SequenceGenerator(name = "utenti_id_gen", sequenceName = "utente_id_utente_seq", allocationSize = 1)
    @Column(name = "id_utente")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ruolo_id")
    private RoleEntity ruolo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(getRuolo());
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
