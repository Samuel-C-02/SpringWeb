package it.pc.test.WebSpringApp.controller;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.dto.security.TokenDTO;
import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.EntityNotFoundException;
import it.pc.test.WebSpringApp.service.AbstractBaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public abstract class AbstractReadController
        <Dto extends AbstractBaseDTO<IdType>, Service extends AbstractBaseService<?, Dto, IdType, ?, ?>, IdType>
        extends AbstractBaseController {

    public abstract Service getService();

    @GetMapping("/all")
    public List<Dto> getAll() {
        return getService().findAllBase();
    }

    @GetMapping("/{Id}")
    public Dto getAllById(@PathVariable(name = "Id") IdType id) {
        try {
            return getService().findByIdBase(id);
        } catch (EntityNotFoundException exc) {
            throw new BadRequestException(exc.getError(), exc);
        }
    }

    @GetMapping("/paged")
    public List<Dto> getAllPaginated(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return getService().findAllPaged(pageNumber, pageSize);
    }

}
