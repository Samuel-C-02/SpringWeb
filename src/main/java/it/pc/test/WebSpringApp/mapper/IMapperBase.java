package it.pc.test.WebSpringApp.mapper;

import java.util.List;

public interface IMapperBase<E, D> {

    D entityToDTO(E e);

    List<D> entityToDTO(List<E> eList);

    E dtoToEntity(D d);

    List<E> dtoToEntity(List<D> dList);


}
