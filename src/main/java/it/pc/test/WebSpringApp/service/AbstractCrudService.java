package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.mapper.IMapperBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Abstract Class for CRUD Operations in services
 *
 * @param <E> Entity
 * @param <D> Dto
 * @param <I> Id type
 * @param <M> Mapper (dto-entity conversion)
 * @param <R> Repository (extends JPA)
 */
public abstract class AbstractCrudService
        <E, D, I, M extends IMapperBase<E, D>, R extends JpaRepository<E, I>>
        extends AbstractBaseService<E, D, I, M, R> {

    /**
     * Basic INSERT operation with null check.
     * <br> Override if additional logic is needed </br>
     *
     * @param objToSave DTO to save
     * @return saved DTO
     */
    public D insert(D objToSave) {
        if (objToSave == null) {
            throw new BadRequestException(new HttpErroreMessage("INSERT ERROR. param is null"));
        }
        // Convert to Entity and save it in the database. Convert to DTO again and return the saved obj
        E savedObj = getRepository().save(getMapper().dtoToEntity(objToSave));
        return getMapper().entityToDTO(savedObj);
    }

    /**
     * Basic INSERT ALL operation with null/empty check.
     * <br> Override if additional logic is needed </br>
     *
     * @param objListToSave DTO List to save
     * @return saved DTO List
     */
    public List<D> insertAll(List<D> objListToSave) {
        if (objListToSave == null || objListToSave.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("INSERT ALL ERROR. param is null or empty"));
        }
        // Convert to Entity and save it in the database. Convert to DTO again and return the saved obj
        List<E> saveObjList = getRepository().saveAll(getMapper().dtoToEntity(objListToSave));
        return getMapper().entityToDTO(saveObjList);
    }

}
