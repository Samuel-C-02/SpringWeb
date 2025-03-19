package it.pc.test.WebSpringApp.service;


import it.pc.test.WebSpringApp.dto.SedeDTO;
import it.pc.test.WebSpringApp.entity.SedeEntity;
import it.pc.test.WebSpringApp.repository.SedeRepository;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class SedeServiceTest {

    @Mock
    SedeRepository sedeRepository;
    @InjectMocks
    SedeService sedeService;

    private List<SedeEntity> getMockEntities() {
        List<SedeEntity> sedeMockList = new ArrayList<>();
        SedeEntity sedeMock1 = new SedeEntity();
        sedeMock1.setId(1);
        sedeMock1.setIndirizzo("Indirizzo Sede 1");
        sedeMock1.setDataCostruzione(LocalDate.of(2000, 1, 1));
        sedeMock1.setValoreImmobile(100000d);
        sedeMock1.setNazione("IT");
        sedeMockList.add(sedeMock1);
        SedeEntity sedeMock2 = new SedeEntity();
        sedeMock2.setId(2);
        sedeMock2.setIndirizzo("Indirizzo Sede 2");
        sedeMock2.setDataCostruzione(LocalDate.of(2002, 2, 2));
        sedeMock2.setValoreImmobile(200000d);
        sedeMock2.setNazione("US");
        sedeMockList.add(sedeMock2);
        return sedeMockList;
    }

    private List<SedeDTO> getMockDTOs(){
       return sedeService.getMapper().entityToDTO(getMockEntities());
    }

    @Test
    public void findAllByIdTest(){
        Mockito.when(sedeRepository.findById(Mockito.any()))
                .thenReturn(Optional.ofNullable(getMockEntities().get(0)));

        SedeDTO byId = sedeService.findByIdBase(1);

        Assertions.assertEquals(1, byId.getId());
        Assertions.assertEquals("Indirizzo Sede 1", byId.getIndirizzo());


    }


}
