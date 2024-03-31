package it.pc.test.WebSpringApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Immutable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tipo_prodotto")
@Data
@Immutable
public class TipoProdottoEntity  extends AbstractBaseEntity{

    @Id
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "tipo_prodotto")
    private String tipoProdotto;

}
