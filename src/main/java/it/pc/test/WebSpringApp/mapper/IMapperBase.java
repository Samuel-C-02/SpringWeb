package it.pc.test.WebSpringApp.mapper;

import it.pc.test.WebSpringApp.dto.ProduttoreDTO;
import it.pc.test.WebSpringApp.entity.ProduttoreEntity;

import java.util.List;

public interface IMapperBase<E, D> {

    D entityToDTO(E e);
    List<D> entityToDTO(List<E> eList);

}
