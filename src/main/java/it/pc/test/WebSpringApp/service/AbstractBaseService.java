package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.mapper.IMapperBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class AbstractBaseService<E, D, I, M extends IMapperBase<E, D>, R extends JpaRepository<E, I>> {

    public List<D> findAllBase() {
        return getMapper().entityToDTO(getRepository().findAll());
    }

    public D findByIdBase(I id) {
        return getMapper().entityToDTO(getRepository().findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Errore, l'id inserito non esiste. Id: " + id)));
    }

    public abstract M getMapper();

    public abstract R getRepository();
}
