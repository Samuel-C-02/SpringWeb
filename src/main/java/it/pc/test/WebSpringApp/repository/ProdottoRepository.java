package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.entity.ProdottoEntity;
import it.pc.test.WebSpringApp.enums.Provenienza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProdottoRepository extends JpaRepository<ProdottoEntity, Integer> {

    @Query(value = "SELECT * FROM  web.Prodotto p WHERE p.produttore_id IN (?1)", nativeQuery = true)
    List<ProdottoEntity> findAllByProduttoreId(Set<Integer> id);

    @Query(value = "SELECT * FROM web.Prodotto p WHERE p.disponibile IS TRUE", nativeQuery = true)
    List<ProdottoEntity> getAllProdottiDisponibili();

    @Query("SELECT p FROM ProdottoEntity p WHERE p.provenienza = ?1")
    List<ProdottoEntity> getAllProdottiByProvenienza(Provenienza p);

    @Query("SELECT p FROM ProdottoEntity p WHERE p.tipoProdotto.id = ?1")
    List<ProdottoEntity> getAllProdottiByTipoProdottoId(Integer id);
}
