package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.PartnerDTO;
import it.pc.test.WebSpringApp.entity.PartnerEntity;
import it.pc.test.WebSpringApp.mapper.PartnerMapper;
import it.pc.test.WebSpringApp.repository.PartnerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerService extends AbstractBaseService<
        PartnerEntity,
        PartnerDTO,
        Integer,
        PartnerMapper,
        PartnerRepository>
{

    final PartnerRepository partnerRepository;
    final PartnerMapper partnerMapper = Mappers.getMapper(PartnerMapper.class);

    @Autowired
    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public PartnerMapper getMapper() {
        return partnerMapper;
    }

    @Override
    public PartnerRepository getRepository() {
        return partnerRepository;
    }
}
