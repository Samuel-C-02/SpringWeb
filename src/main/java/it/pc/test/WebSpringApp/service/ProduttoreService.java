package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.entity.ProduttoreEntity;
import it.pc.test.WebSpringApp.mapper.ProduttoreMapper;
import it.pc.test.WebSpringApp.repository.ProduttoreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduttoreService extends AbstractBaseService<
        ProduttoreEntity,
        ProduttoreDTO,
        Integer,
        ProduttoreMapper,
        ProduttoreRepository> {

    final ProduttoreRepository produttoreRepository;
    final ProduttoreMapper produttoreMapper = Mappers.getMapper(ProduttoreMapper.class);
    final ProdottoService prodottoService;

    @Autowired
    public ProduttoreService(ProduttoreRepository produttoreRepository, ProdottoService prodottoService) {
        this.produttoreRepository = produttoreRepository;
        this.prodottoService = prodottoService;
    }

    public ProduttoreDTO getProduttoriWithProdotti(Integer id) {
        ProduttoreDTO produttoreById = findByIdBase(id);
        List<ProdottoDTO> prodottiByProduttoreId = prodottoService.findAllProdottiByProduttoreId(id);
        produttoreById.setProdotti(prodottiByProduttoreId);
        return produttoreById;
    }

    @Override
    public ProduttoreMapper getMapper() {
        return produttoreMapper;
    }

    @Override
    public ProduttoreRepository getRepository() {
        return produttoreRepository;
    }

    public ProduttoreDTO getProduttoreByNome(String nome) {
        return produttoreMapper.entityToDTO(produttoreRepository.findProduttoreByNome(nome));
    }

}
