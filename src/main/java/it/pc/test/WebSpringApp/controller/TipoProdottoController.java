package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.TipoProdottoDTO;
import it.pc.test.WebSpringApp.service.TipoProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tipo_prodotto")
public class TipoProdottoController extends AbstractReadController<TipoProdottoDTO, TipoProdottoService, Integer> {
    @Autowired
    private TipoProdottoService tipoProdottoService;

    @Override
    public TipoProdottoService getService() {
        return tipoProdottoService;
    }
}
