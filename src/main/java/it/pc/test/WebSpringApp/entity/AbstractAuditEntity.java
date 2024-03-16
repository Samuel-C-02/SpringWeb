package it.pc.test.WebSpringApp.entity;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostUpdate;
import lombok.Data;

import java.time.LocalDateTime;

@MappedSuperclass
@Data
public abstract class AbstractBaseEntity {

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
