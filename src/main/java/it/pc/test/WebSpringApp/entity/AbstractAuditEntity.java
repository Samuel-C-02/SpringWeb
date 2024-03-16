package it.pc.test.WebSpringApp.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@Data
public abstract class AbstractAuditEntity extends AbstractBaseEntity{

    @Column(name = "data_inserimento")
    private LocalDateTime dataInserimento;

    @Column(name = "data_modifica")
    private LocalDateTime dataModifica;

    @PostConstruct
    public void createdEntity(){
        setDataInserimento(LocalDateTime.now());
    }

    @PostUpdate
    public void updatedEntity(){
        setDataModifica(LocalDateTime.now());
    }

}
