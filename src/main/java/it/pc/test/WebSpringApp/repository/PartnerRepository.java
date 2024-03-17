package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.PartnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<PartnerEntity, Integer> {
}
