package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.ProduttoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduttoreRepository extends JpaRepository<ProduttoreEntity, Integer> {
}
