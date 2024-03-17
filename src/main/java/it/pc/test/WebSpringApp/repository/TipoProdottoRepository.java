package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.TipoProdottoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProdottoRepository extends JpaRepository<TipoProdottoEntity, Integer> {
}
