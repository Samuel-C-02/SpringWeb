package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.PartnerDTO;
import it.pc.test.WebSpringApp.entity.PartnerEntity;
import org.mapstruct.Mapper;

@Mapper
public interface PartnerMapper extends IMapperBase<PartnerEntity, PartnerDTO> {
}
