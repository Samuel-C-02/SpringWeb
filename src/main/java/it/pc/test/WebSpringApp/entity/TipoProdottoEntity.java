package it.pc.test.WebSpringApp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "sede")
@Data
@Immutable
public class TipoProdottoEntity {

    @Id
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "tipo_prodotto")
    private String tipoProdotto;

}
