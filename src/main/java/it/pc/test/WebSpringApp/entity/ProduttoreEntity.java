package it.pc.test.WebSpringApp.entity;

import it.pc.test.WebSpringApp.enums.TipoCategoria;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "produttore")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduttoreEntity extends AbstractAuditEntity<Integer> {

    @Id
    @Column(name = "id_produttore")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produttore_id_generator")
    @SequenceGenerator(name = "produttore_id_generator", sequenceName = "prodotto_id_prodotto_seq", allocationSize = 1)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String descrizione;

    @JoinColumn(name = "partner_id")
    @ManyToOne
    private PartnerEntity partner;

    @Column(name = "tipo_categoria")
    private TipoCategoria categoria;

    @Column(name = "n_dipendenti")
    private Integer numeroDipendenti;

    @Column(name = "fatturato_annuo")
    private Double fatturatoAnnuo;

    @JoinColumn(name = "sede_id")
    @ManyToOne
    private SedeEntity sede;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduttoreEntity that = (ProduttoreEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
