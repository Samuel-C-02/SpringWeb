package it.pc.test.WebSpringApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "partner")
@Data
public class PartnerEntity extends AbstractBaseEntity {

    @Id
    @Column(name = "id_partner")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_id_generator")
    @SequenceGenerator(name = "partner_id_generator", sequenceName = "partner_id_partner_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_fondazione")
    private LocalDate dataFondazione;

    @Column(name = "n_dipendenti")
    private Double numeroDipendenti;

    @Column(name = "fatturato_annuo")
    private Double fatturatoAnnuo;

    @JoinColumn(name = "sede_id")
    @ManyToOne
    private SedeEntity sede;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PartnerEntity that = (PartnerEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }
}
