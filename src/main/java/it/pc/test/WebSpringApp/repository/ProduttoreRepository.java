package it.pc.test.WebSpringApp.repository;

import it.pc.test.WebSpringApp.dto.projection.ProduttoreBasicInfo;
import it.pc.test.WebSpringApp.entity.ProduttoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProduttoreRepository extends JpaRepository<ProduttoreEntity, Integer> {

    ProduttoreEntity findProduttoreByNome(String nome);

    @Query(value = """ 
                   SELECT
                   p.id_produttore as id_produttore, 
                   p.nome as nome_produttore, 
                   p.tipo_categoria as tipo_categoria_id,
                   s.id_sede as sede_id, 
                   s.indirizzo as sede_indirizzo, 
                   s.nazione as sede_nazione,
                   prt.nome as nome_partner,
                   sp.indirizzo as indirizzo_partner
                   FROM web.produttore p 
                   JOIN web.sede s ON p.sede_id = s.id_sede
                   JOIN web.partner prt ON prt.id_partner = p.partner_id
                   JOIN web.sede sp ON prt.sede_id = sp.id_sede
                   WHERE p.id_produttore IN (?1)""", nativeQuery = true)
    List<ProduttoreBasicInfo> getAllProduttoriBasicInfoByIds(Set<Integer> ids);


}
