package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.entity.AbstractBaseEntity;
import it.pc.test.WebSpringApp.exceptions.EntityNotFoundException;
import it.pc.test.WebSpringApp.mapper.IMapperBase;
import org.springframework.data.domain.PageRequest;
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

    /**
     * Retrieves a paginated list of entities and converts them to DTOs.
     * <p>
     * This method fetches a specific page of entities from the repository based on the provided page number and size,
     * and then converts them into DTOs.
     * </p>
     *
     * @param pageNumber page number
     * @param pageSize   number of elements in a page
     */
    public List<D> findAllPaged(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return getMapper().entityToDTO(getRepository().findAll(pageRequest).getContent());
    }

    public abstract M getMapper();

    public abstract R getRepository();
}
