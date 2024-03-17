package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Integer> {
}
