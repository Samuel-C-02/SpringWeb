package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import it.pc.test.WebSpringApp.enums.Provenienza;
import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.mapper.ProdottoMapper;
import it.pc.test.WebSpringApp.repository.ProdottoRepository;
import it.pc.test.WebSpringApp.utils.LogUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (id == null) {
            throw new BadRequestException(new HttpErroreMessage("Produttore Id is NULL"));
        }
        return prodottoMapper.entityToDTO(prodottoRepository.findAllByProduttoreId(id));
    }

    public List<ProdottoDTO> getAllProdottiDisponibili() {
        return prodottoMapper.entityToDTO(prodottoRepository.getAllProdottiDisponibili());
    }

    public List<ProdottoDTO> getAllProdottiByProvenienza(Provenienza p) {
        if (p == null) {
            throw new BadRequestException(new HttpErroreMessage("Provenienza is NULL"));
        }
        return prodottoMapper.entityToDTO(prodottoRepository.getAllProdottiByProvenienza(p));
    }

    public List<ProdottoDTO> getAllProdottiByTipoProdottoId(Integer id) {
        if (id == null) {
            throw new BadRequestException(new HttpErroreMessage("TipoProdotto Id is NULL"));
        }
        return prodottoMapper.entityToDTO(prodottoRepository.getAllProdottiByTipoProdottoId(id));
    }

}
