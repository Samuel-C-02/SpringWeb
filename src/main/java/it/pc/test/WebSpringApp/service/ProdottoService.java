package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import it.pc.test.WebSpringApp.mapper.ProdottoMapper;
import it.pc.test.WebSpringApp.repository.ProdottoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdottoService extends AbstractBaseService<ProdottoEntity, ProdottoDTO, Integer, ProdottoMapper, ProdottoRepository> {

    final ProdottoRepository prodottoRepository;
    final ProdottoMapper prodottoMapper = Mappers.getMapper(ProdottoMapper.class);


    @Autowired
    public ProdottoService(ProdottoRepository prodottoRepository) {
        this.prodottoRepository = prodottoRepository;
    }

    @Override
    public ProdottoMapper getMapper() {
        return prodottoMapper;
    }

    @Override
    public ProdottoRepository getRepository() {
        return prodottoRepository;
    }

    public List<ProdottoDTO> findAllProdottiByProduttoreId(Integer id) {
        return getMapper().entityToDTO(getRepository().findAllByProduttoreId(id));
    }
}
