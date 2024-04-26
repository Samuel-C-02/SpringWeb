package it.pc.test.WebSpringApp.controller;


import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.service.ProduttoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produttore")
public class ProduttoreController extends AbstractReadController<ProduttoreDTO, ProduttoreService, Integer> {

    @Autowired
    private ProduttoreService produttoreService;


    @GetMapping("/{idProduttore}/with-products")
    public ProduttoreDTO getProduttoreWithProdotti(@PathVariable(name = "idProduttore") Integer id) {
        return produttoreService.getProduttoriWithProdotti(id);
    }

    @Override
    public ProduttoreService getService() {
        return produttoreService;
    }
}
