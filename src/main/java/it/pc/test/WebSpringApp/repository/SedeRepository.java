package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.SedeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepository extends JpaRepository<SedeEntity, Integer> {
}
