package it.pc.test.WebSpringApp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "sede")
@Data
public class SedeEntity extends AbstractBaseEntity {

    @Id
    @Column(name = "id_sede")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sede_id_generator")
    @SequenceGenerator(name = "sede_id_generator", sequenceName = "sede_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome_sede")
    private String nomeSede;

    @Column(name = "indirizzo_sede")
    private String indirizzoSede;

    @Column(name = "fatturato_avg")
    private BigDecimal fatturatoMedioAnnuale;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SedeEntity that = (SedeEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
