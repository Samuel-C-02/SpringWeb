package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.ProduttoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduttoreRepository extends JpaRepository<ProduttoreEntity, Integer> {

    List<ProduttoreEntity> findProduttoreByNome(String nome);


}
