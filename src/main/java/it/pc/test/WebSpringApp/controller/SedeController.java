package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.SedeDTO;
import it.pc.test.WebSpringApp.service.SedeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/sede")
public class SedeController extends AbstractBaseController<SedeDTO, SedeService, Integer> {

    private SedeService sedeService;

    @Override
    public SedeService getService() {
        return sedeService;
    }
}
