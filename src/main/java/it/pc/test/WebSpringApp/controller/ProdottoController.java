package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.dto.grid.GridRequest;
import it.pc.test.WebSpringApp.enums.Provenienza;
import it.pc.test.WebSpringApp.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotto")
public class ProdottoController extends AbstractCrudController<ProdottoDTO, ProdottoService, Integer> {

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
    public List<ProdottoDTO> getAllProdottiByProvenienza(@RequestParam(name = "provenienza") Provenienza p) {
        return getService().getAllProdottiByProvenienza(p);
    }

    @GetMapping("all/tipo/{tipoId}")
    public List<ProdottoDTO> getAllProdottiByTipoProdottoId(@PathVariable(name = "tipoId") Integer tipoId) {
        return getService().getAllProdottiByTipoProdottoId(tipoId);
    }

    @PostMapping("/grid")
    public Page<ProdottoDTO> getGrigliaProdotti(@RequestBody GridRequest richiesta){
        return getService().getProdottiGrid(richiesta);
    }
}
