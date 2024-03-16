package it.pc.test.WebSpringApp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "partner")
@Data
public class PartnerEntity extends AbstractAuditEntity {

    @Id
    @Column(name = "id_partner")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "partner_id_generator")
    @SequenceGenerator(name = "partner_id_generator", sequenceName = "partner_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @JoinColumn(name = "sede_id")
    @ManyToOne
    private SedeEntity sede;

    @Column(name = "data_fondazione")
    private LocalDate dataFondazione;

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
