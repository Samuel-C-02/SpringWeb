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
public class ProduttoreEntity {

    @Id
    @Column(name = "id_produttore")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produttore_id_generator")
    @SequenceGenerator(name = "produttore_id_generator", sequenceName = "produttore_id_seq", allocationSize = 1)
    private Integer id;

    @Column
    private String nome;

    @Column
    private String descrizione;

    @JoinColumn(name = "partner_id")
    @OneToOne
    private PartnerEntity partner;

    private TipoCategoria categoria;

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
