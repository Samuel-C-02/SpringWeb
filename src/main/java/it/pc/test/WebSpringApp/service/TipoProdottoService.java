package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.TipoProdottoDTO;
import it.pc.test.WebSpringApp.entity.TipoProdottoEntity;
import it.pc.test.WebSpringApp.mapper.TipoProdottoMapper;
import it.pc.test.WebSpringApp.repository.TipoProdottoRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProdottoService extends AbstractBaseService<
        TipoProdottoEntity,
        TipoProdottoDTO,
        Integer,
        TipoProdottoMapper,
        TipoProdottoRepository> {

    final TipoProdottoRepository tipoProdottoRepository;
    final TipoProdottoMapper tipoProdottoMapper = Mappers.getMapper(TipoProdottoMapper.class);

    @Autowired
    public TipoProdottoService(TipoProdottoRepository tipoProdottoRepository) {
        this.tipoProdottoRepository = tipoProdottoRepository;
    }

    @Override
    public TipoProdottoMapper getMapper() {
        return tipoProdottoMapper;
    }

    @Override
    public TipoProdottoRepository getRepository() {
        return tipoProdottoRepository;
    }
}
