package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController extends AbstractBaseController<ProdottoDTO, ProdottoService, Integer> {

    @Autowired
    private ProdottoService prodottoService;

    @Override
    public ProdottoService getService() {
        return prodottoService;
    }
}
