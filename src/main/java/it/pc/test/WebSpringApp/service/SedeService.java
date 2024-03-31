package it.pc.test.WebSpringApp.service;

import it.pc.test.WebSpringApp.dto.SedeDTO;
import it.pc.test.WebSpringApp.entity.SedeEntity;
import it.pc.test.WebSpringApp.mapper.SedeMapper;
import it.pc.test.WebSpringApp.repository.SedeRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SedeService extends AbstractBaseService<SedeEntity, SedeDTO, Integer, SedeMapper, SedeRepository> {

    final SedeRepository sedeRepository;
    final SedeMapper sedeMapper = Mappers.getMapper(SedeMapper.class);

    @Autowired
    public SedeService(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    @Override
    public SedeMapper getMapper() {
        return sedeMapper;
    }

    @Override
    public SedeRepository getRepository() {
        return sedeRepository;
    }
}
