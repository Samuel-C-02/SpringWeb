package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.mapper.ProduttoreMapper;
import it.pc.test.WebSpringApp.repository.ProduttoreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduttoreService {

    final ProduttoreRepository produttoreRepository;
    final ProduttoreMapper produttoreMapper = Mappers.getMapper(ProduttoreMapper.class);

    @Autowired
    public ProduttoreService(ProduttoreRepository produttoreRepository) {
        this.produttoreRepository = produttoreRepository;
    }

    public List<ProduttoreDTO> getAllProduttori() {

      return   produttoreMapper.entityToDTO(produttoreRepository.findAll());
    }
}
