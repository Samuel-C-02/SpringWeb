package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.entity.ProduttoreEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ProduttoreMapper {

    ProduttoreDTO entityToDTO(ProduttoreEntity e);
    List<ProduttoreDTO> entityToDTO(List<ProduttoreEntity> eList);


}
