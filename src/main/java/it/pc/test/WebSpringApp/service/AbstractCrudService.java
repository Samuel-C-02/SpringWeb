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
     * <strong> Basic INSERT operation with null/empty check.
     * <br><font color="red"> OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</font> </br>
     * </strong>
     *
     * @param objToSave DTO to save
     * @return saved DTO
     */
    @Transactional
    public D insert(D objToSave) {
        if (objToSave == null) {
            throw new BadRequestException(new HttpErroreMessage("INSERT ERROR. param is null"));
        }
        // Convert to Entity and save it in the database. Convert to DTO again and return the saved obj
        E savedObj = getRepository().save(getMapper().dtoToEntity(objToSave));
        return getMapper().entityToDTO(savedObj);
    }

    /**
     * <strong> Basic INSERT ALL operation with null/empty check.
     * <br><font color="red"> OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</font> </br>
     * </strong>
     *
     * @param objListToSave DTO List to save
     * @return saved DTO List
     */
    @Transactional
    public List<D> insertAll(List<D> objListToSave) {
        if (objListToSave == null || objListToSave.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("INSERT ALL ERROR. param is null or empty"));
        }
        // Convert to Entity and save it in the database. Convert to DTO again and return the saved obj
        List<E> saveObjList = getRepository().saveAll(getMapper().dtoToEntity(objListToSave));
        return getMapper().entityToDTO(saveObjList);
    }

    /**
     * <strong> Basic UPDATE  operation with param null check, Id mismatch check and Id already in the db check .
     * <br><font color="red"> OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</font> </br>
     * </strong>
     *
     * @param id          Id of which entity to update
     * @param objToUpdate Object to Update
     * @return Updated Object
     */
    @Transactional
    public D update(I id, D objToUpdate) {
        if (id == null || objToUpdate == null) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ERROR. Id or Obj is null"));
        }
        // If the Id is specified and different from id param
        if (objToUpdate.getId() != null && (id != objToUpdate.getId())) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ERROR. Id mismatch between Param Id and DTO Id"));
        }
        // Check Id is not already in the database
        if (getRepository().existsById(id)) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ERROR. Id already in the database. Id " + id));
        }
        // - - - To check other parameters and foreign key, override the method - - -

        E savedObject = getRepository().save(getMapper().dtoToEntity(objToUpdate));

        return getMapper().entityToDTO(savedObject);
    }

    /**
     * * <strong> Basic UPDATE ALL operation with param/Id null check,  Id already in the db check .
     * * <br><font color="red"> OVERRIDE IF ADDITIONAL LOGIC IS NEEDED</font> </br>
     * * </strong>
     *
     * @param objListToUpdate List of object to update
     * @return Update list
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
        // Check if there is an object already in the db
        if (objListToUpdate.stream().anyMatch(dto -> getRepository().existsById(dto.getId()))) {
            throw new BadRequestException(new HttpErroreMessage("UPDATE ALL ERROR. Found an Id already in the database"));
        }
        // - - - To check other parameters and foreign key, override the method - - -

        List<E> savedList = getRepository().saveAll(getMapper().dtoToEntity(objListToUpdate));

        return getMapper().entityToDTO(savedList);
    }

}
