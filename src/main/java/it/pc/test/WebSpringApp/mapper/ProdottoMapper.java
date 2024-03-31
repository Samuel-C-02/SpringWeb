package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.ProdottoDTO;
import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProdottoMapper extends IMapperBase<ProdottoEntity, ProdottoDTO> {
}
