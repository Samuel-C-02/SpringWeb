package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.dto.grid.GridRequest;
import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import it.pc.test.WebSpringApp.enums.Provenienza;
import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.mapper.ProdottoMapper;
import it.pc.test.WebSpringApp.repository.ProdottoRepository;
import it.pc.test.WebSpringApp.repository.grid.GridSpecification;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ProdottoService extends AbstractCrudService<ProdottoEntity, ProdottoDTO, Integer, ProdottoMapper, ProdottoRepository> {

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
        return findAllProdottiByProduttoreId(Set.of(id));
    }

    /**
     * Fetch all Prodotti with ProduttoreIds given in the parameter
     *
     * @param ids Produttore IDs
     */
    public List<ProdottoDTO> findAllProdottiByProduttoreId(Set<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BadRequestException(new HttpErroreMessage("Produttore Id is NULL or empty"));
        }
        return prodottoMapper.entityToDTO(prodottoRepository.findAllByProduttoreId(ids));
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

    /**
     * Crea l'oggetto paginato, filtrato e ordinano per creare la Griglia in base ai parametri ricevuti dalla richiesta
     *
     * @param richiestaGriglia Oggetti contenente filtri, ordinamenti, etc...
     */
    public Page<ProdottoDTO> getProdottiGrid(GridRequest richiestaGriglia) {

        Sort sortOrder = GridSpecification.buildSort(richiestaGriglia);

        PageRequest pageRequest = PageRequest.of(
                richiestaGriglia.getPageNumber(), // n pagina
                richiestaGriglia.getPageSize(), // n elementi
                sortOrder); // Sort applicato

        Specification<ProdottoEntity> whereGrid = GridSpecification.buildGridQuery(richiestaGriglia);// Applica filtri (crea la WHERE da passare a JPA)
        Page<ProdottoEntity> pagedGrid = prodottoRepository.findAll(whereGrid, pageRequest);
        return pagedGrid.map(prodottoMapper::entityToDTO);

    }

    @Override
    public ProdottoDTO insert(ProdottoDTO newProdotto) {
        if (newProdotto == null || newProdotto.getProduttoreId() == null) {
            throw new BadRequestException(new HttpErroreMessage("Error saving the ProdottoDTO. Prodotto Received: " + newProdotto));
        }

        ProdottoEntity savedProdotto = prodottoRepository.save(prodottoMapper.dtoToEntity(newProdotto));
        return prodottoMapper.entityToDTO(savedProdotto);
    }

    @Override
    public List<ProdottoDTO> insertAll(List<ProdottoDTO> newProdotti) {
        if (newProdotti == null || newProdotti.isEmpty() || newProdotti.stream().anyMatch(p -> p.getProduttoreId() == null)) {
            throw new BadRequestException(new HttpErroreMessage("Error saving the Prodotto List. List Received: " + newProdotti));
        }

        List<ProdottoEntity> savedProdottiList = prodottoRepository.saveAll(prodottoMapper.dtoToEntity(newProdotti));
        return prodottoMapper.entityToDTO(savedProdottiList);
    }

}
