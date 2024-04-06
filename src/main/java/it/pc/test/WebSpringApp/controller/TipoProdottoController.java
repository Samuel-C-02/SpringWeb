package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.TipoProdottoDTO;
import it.pc.test.WebSpringApp.service.TipoProdottoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tipo_prodotto")
public class TipoProdottoController extends AbstractBaseController<TipoProdottoDTO, TipoProdottoService, Integer> {

    private TipoProdottoService tipoProdottoService;

    @Override
    public TipoProdottoService getService() {
        return tipoProdottoService;
    }
}
