package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.TipoProdottoDTO;
import it.pc.test.WebSpringApp.entity.TipoProdottoEntity;
import org.mapstruct.Mapper;

@Mapper
public interface TipoProdottoMapper extends IMapperBase<TipoProdottoEntity, TipoProdottoDTO> {
}
