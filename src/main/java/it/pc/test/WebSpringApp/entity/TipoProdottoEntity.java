package it.pc.test.WebSpringApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

import java.util.Objects;


@Entity
@Table(name = "tipo_prodotto")
@Data
@Immutable
public class TipoProdottoEntity extends AbstractBaseEntity {

    @Id
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "tipo_prodotto")
    private String tipoProdotto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TipoProdottoEntity that = (TipoProdottoEntity) o;
        return Objects.equals(idTipo, that.idTipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idTipo);
    }
}
