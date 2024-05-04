package it.pc.test.WebSpringApp.controller;


import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.dto.projection.ProduttoreBasicInfo;
import it.pc.test.WebSpringApp.service.ProduttoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/produttore")
public class ProduttoreController extends AbstractReadController<ProduttoreDTO, ProduttoreService, Integer> {

    @Autowired
    private ProduttoreService produttoreService;


    @GetMapping("/{idProduttore}/with-products")
    public ProduttoreDTO getProduttoreWithProdotti(@PathVariable(name = "idProduttore") Integer id) {
        return produttoreService.getProduttoriWithProdotti(id);
    }

    @GetMapping("/nome")
    public ProduttoreDTO getProduttoreByNome(@RequestParam(name = "nome") String nome) {
        return produttoreService.getProduttoreByNome(nome);
    }

    @GetMapping("/basic-info")
    public List<ProduttoreBasicInfo> getAllProduttoriBasicInfoByIds(@RequestParam(name = "produttoreIds") Set<Integer> produttoreIds) {
        return produttoreService.getAllProduttoriBasicByIds(produttoreIds);
    }

    @Override
    public ProduttoreService getService() {
        return produttoreService;
    }
}
