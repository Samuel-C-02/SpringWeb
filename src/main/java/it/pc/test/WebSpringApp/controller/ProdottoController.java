package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.enums.Provenienza;
import it.pc.test.WebSpringApp.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController extends AbstractReadController<ProdottoDTO, ProdottoService, Integer> {

    @Autowired
    private ProdottoService prodottoService;

    @Override
    public ProdottoService getService() {
        return prodottoService;
    }

    @GetMapping("/all/disponibili")
    public List<ProdottoDTO> getAllProdottiDisponibili() {
        return getService().getAllProdottiDisponibili();
    }

    @GetMapping("/all/provenienza")
    public List<ProdottoDTO> getAllProdottiByProvenienza(@RequestParam(name = "provenienza") Provenienza p){
        return getService().getAllProdottiByProvenienza(p);
    }

}
