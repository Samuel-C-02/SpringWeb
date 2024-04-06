package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.service.AbstractBaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public abstract class AbstractBaseController<
        Dto extends AbstractBaseDTO<IdType>,
        Service extends AbstractBaseService<?, Dto, IdType, ?, ?>,
        IdType> {

    @GetMapping("/test")
    public String check() {
        return "OK";
    }

    public abstract Service getService();

    @GetMapping("/all")
    public List<Dto> getAll() {
        return getService().findAllBase();
    }

    @GetMapping("/{Id}")
    public Dto getAllById(@PathVariable(name = "Id") IdType id) {
        return getService().findByIdBase(id);
    }


}
