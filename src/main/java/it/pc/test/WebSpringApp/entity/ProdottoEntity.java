package it.pc.test.WebSpringApp.entity;

import it.pc.test.WebSpringApp.enums.Provenienza;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@Entity
@Table(name = "prodotto")
public class ProdottoEntity extends AbstractAuditEntity {

    @Id
    @Column(name = "id_prodotto")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prodotto_id_generator")
    @SequenceGenerator(name = "prodotto_id_generator", sequenceName = "prodotto_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome_prodotto")
    private String nomeProdotto;

    @Column(name = "costo")
    private BigDecimal costo;

    @Column(name = "disponibile")
    private boolean disponibile;

    @Column(name = "provenienza")
    private Provenienza provenienza;

    @JoinColumn(name = "tipo_prodotto_id")
    @ManyToOne
    private TipoProdottoEntity tipoProdotto;

    @Column(name = "produttore_id")
    private Integer produttoreId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProdottoEntity that = (ProdottoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
