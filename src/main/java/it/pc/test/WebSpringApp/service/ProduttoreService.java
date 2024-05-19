package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.dto.projection.ProduttoreBasicInfo;
import it.pc.test.WebSpringApp.entity.ProduttoreEntity;
import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.mapper.ProduttoreMapper;
import it.pc.test.WebSpringApp.repository.ProduttoreRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProduttoreService extends AbstractCrudService<
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

    public List<ProduttoreBasicInfo> getAllProduttoriBasicByIds(Set<Integer> ids) {
        return produttoreRepository.getAllProduttoriBasicInfoByIds(ids);
    }

    @Override
    public ProduttoreDTO insert(ProduttoreDTO objToSave) {
        if (objToSave == null || objToSave.getPartner() == null || objToSave.getSede() == null) {
            throw new BadRequestException(new HttpErroreMessage("Error saving ProduttoreDTO. param is null o incomplete");
        }

        ProduttoreEntity savedProduttore = produttoreRepository.save(produttoreMapper.dtoToEntity(objToSave));
        return produttoreMapper.entityToDTO(savedProduttore);
    }

    @Override
    public List<ProduttoreDTO> insertAll(List<ProduttoreDTO> objListToSave) {
        if (objListToSave == null || objListToSave.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("Insert Error: ProduttoreDTOList is null or Empty"));
        }

        List<ProduttoreEntity> savedProduttori = produttoreRepository.saveAll(produttoreMapper.dtoToEntity(objListToSave));
        return produttoreMapper.entityToDTO(savedProduttori);
    }
}
