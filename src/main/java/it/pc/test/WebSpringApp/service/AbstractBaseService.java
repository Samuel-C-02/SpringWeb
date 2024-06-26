package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.entity.AbstractBaseEntity;
import it.pc.test.WebSpringApp.exceptions.EntityNotFoundException;
import it.pc.test.WebSpringApp.mapper.IMapperBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Abstract Base class for READ Operations in services
 *
 * @param <E> Entity
 * @param <D> Dto
 * @param <I> Id type
 * @param <M> Mapper (dto-entity conversion)
 * @param <R> Repository (extends JPA)
 */
public abstract class AbstractBaseService<E extends AbstractBaseEntity<I>, D extends AbstractBaseDTO<I>, I, M extends IMapperBase<E, D>,
        R extends JpaRepository<E, I>> {

    public List<D> findAllBase() {
        return getMapper().entityToDTO(getRepository().findAll());
    }

    public D findByIdBase(I id) {
        return getMapper().entityToDTO(getRepository()
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Errore, l'id inserito non esiste. Id: " + id)));
    }

    public abstract M getMapper();

    public abstract R getRepository();
}
