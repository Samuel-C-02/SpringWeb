package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.entity.AbstractBaseEntity;
import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.mapper.IMapperBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

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
        <E extends AbstractBaseEntity<I>, D extends AbstractBaseDTO<I>, I, M extends IMapperBase<E, D>, R extends JpaRepository<E, I>>
        extends AbstractBaseService<E, D, I, M, R> {

    /**
     * Inserts a single new entity into the database after validating the input.
     * <p>
     * This method ensures that:
     * <ul>
     *   <li>The input DTO is not null.</li>
     *   <li>The entity's ID is <b>null</b> (to prevent accidental updates).</li>
     * </ul>
     * The entity is wrapped in a list and processed using {@link #insertAll(List)},
     * then the first saved object is returned.
     * </p>
     * <p><font color="red"><strong>OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</strong></font></p>
     *
     * @param objToSave DTO to be inserted.
     * @return The saved DTO.
     * @throws BadRequestException if the input is null or has a non-null ID.
     */

    @Transactional
    public D insert(D objToSave) {
        List<D> savedObj = insertAll(List.of(objToSave));
        return savedObj.get(0);
    }

    /**
     * Inserts a list of new entities into the database after validating the input parameters.
     * <p>
     * This method ensures that:
     * <ul>
     *   <li>The input list is not null or empty.</li>
     *   <li>Each entity in the list has a <b>null</b> ID (to prevent accidental updates).</li>
     * </ul>
     * The entities are then converted, saved in the database, and returned as DTOs.
     * </p>
     * <p><font color="red"><strong>OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</strong></font></p>
     *
     * @param objListToSave List of DTOs to be inserted.
     * @return List of saved DTOs.
     * @throws BadRequestException if the input list is null/empty or contains entities with non-null IDs.
     */
    @Transactional
    public List<D> insertAll(List<D> objListToSave) {
        if (objListToSave == null || objListToSave.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("INSERT ALL ERROR. param is null or empty"));
        }
        if (objListToSave.stream().anyMatch(o -> o.getId() != null)) {
            throw new BadRequestException(new HttpErroreMessage("INSERT ALL ERROR: ID must be null when inserting a new entity."));
        }

        // Convert to Entity and save it in the database. Convert to DTO again and return the saved obj
        List<E> saveObjList = getRepository().saveAll(getMapper().dtoToEntity(objListToSave));
        return getMapper().entityToDTO(saveObjList);
    }

    /**
     * Updates the entity after validating the input parameters.
     * <p>
     * This method ensures that:
     * <ul>
     *   <li>The input is not null.</li>
     *   <li>The entity has a valid ID.</li>
     *   <li>The ID exists in the database before performing the update.</li>
     * </ul>
     * <p><font color="red"><strong>OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</strong></font></p>
     *
     * @param objToUpdate Object to Update
     * @return Updated Object
     */
    @Transactional
    public D update(D objToUpdate) {
        List<D> updatedObj = updateAll(List.of(objToUpdate));
        return updatedObj.get(0);
    }

    /**
     * Updates a list of entities after validating the input parameters.
     * <p>
     * This method ensures that:
     * <ul>
     *   <li>The input list is not null or empty.</li>
     *   <li>Each entity in the list has a valid ID.</li>
     *   <li>The ID exists in the database before performing the update.</li>
     * </ul>
     * <p><font color="red"><strong>OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</strong></font></p>
     *
     * @param objListToUpdate List of objects to update.
     * @return Updated list of objects.
     */
    @Transactional
    public List<D> updateAll(List<D> objListToUpdate) {
        if (objListToUpdate == null || objListToUpdate.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ALL ERROR. param is null or empty"));
        }
        // Check if the Id is null
        if (objListToUpdate.stream().anyMatch(dto -> dto.getId() == null)) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ALL ERROR. Id null found in the list"));
        }

        // Check if the id exist in the db
        if (objListToUpdate.stream().anyMatch(dto -> !getRepository().existsById(dto.getId()))) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ALL ERROR. Id not found in the database"));
        }
        // - - - To check other parameters and foreign key, override the method - - -

        List<E> savedList = getRepository().saveAll(getMapper().dtoToEntity(objListToUpdate));

        return getMapper().entityToDTO(savedList);
    }

}
