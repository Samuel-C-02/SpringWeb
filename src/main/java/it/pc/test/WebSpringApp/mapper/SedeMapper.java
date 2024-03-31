package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.SedeDTO;
import it.pc.test.WebSpringApp.entity.SedeEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SedeMapper extends IMapperBase<SedeEntity, SedeDTO> {
}
