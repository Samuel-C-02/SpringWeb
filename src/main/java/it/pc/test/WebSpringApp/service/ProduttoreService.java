package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.mapper.ProduttoreMapper;
import it.pc.test.WebSpringApp.repository.ProduttoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduttoreService {

    final ProduttoreRepository produttoreRepository;
    final ProduttoreMapper produttoreMapper;

    @Autowired
    public ProduttoreService(ProduttoreRepository produttoreRepository, ProduttoreMapper produttoreMapper) {
        this.produttoreRepository = produttoreRepository;
        this.produttoreMapper = produttoreMapper;
    }

    public List<ProduttoreDTO> getAllProduttori() {

      return   produttoreMapper.entityToDTO(produttoreRepository.findAll());
    }
}
