package it.pc.test.WebSpringApp.controller;


import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.service.ProduttoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produttore")
public class ProduttoreController extends AbstractBaseController {

    @Autowired
    ProduttoreService produttoreService;

    @GetMapping("/all")
    public List<ProduttoreDTO> getAll(){
        return produttoreService.getAllProduttori();
    }

}
