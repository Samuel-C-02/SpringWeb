package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.service.AbstractCrudService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Abstract Controller for CRUD operation
 *
 * @param <Dto>     DTO that extends AbstractBaseDTO
 * @param <Service> Service that extends AbstractCrudService
 * @param <IdType>  Id type
 */
public abstract class AbstractCrudController
        <Dto extends AbstractBaseDTO<IdType>, Service extends AbstractCrudService<?, Dto, IdType, ?, ?>, IdType>
        extends AbstractReadController<Dto, Service, IdType> {

    @PostMapping("/new")
    public Dto insertDto(@RequestBody Dto objToSave) {
        if (objToSave == null) {
            throw new BadRequestException(new HttpErroreMessage("Received Object is null"));
        }

        return getService().insert(objToSave);
    }

    @PostMapping("/new-batch")
    public List<Dto> insertAllDto(@RequestBody List<Dto> objListToSave) {
        if (objListToSave == null || objListToSave.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("Received List is null or Empty"));
        }

        return getService().insertAll(objListToSave);
    }

    @PutMapping("/update/{id}")
    public Dto update(@PathVariable(name = "id") IdType id, @RequestBody Dto objToUpdate) {
        if (id == null || objToUpdate == null) {
            throw new BadRequestException(new HttpErroreMessage("Received id/obj is null. Id: " + id + " Obj: " + objToUpdate));
        }

        return getService().update(id, objToUpdate);
    }

    @PutMapping("/update-batch")
    public List<Dto> updateAll(@RequestBody List<Dto> listToUpdate) {
        if (listToUpdate == null || listToUpdate.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("Received list is null or empty"));
        }

        return getService().updateAll(listToUpdate);
    }

}
