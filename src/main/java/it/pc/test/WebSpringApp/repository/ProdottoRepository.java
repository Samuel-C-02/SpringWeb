package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Integer> {

    List<ProdottoEntity> findAllByProduttoreId(Integer id);

}
