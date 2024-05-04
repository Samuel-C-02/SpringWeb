package it.pc.test.WebSpringApp.dto.projection;

import it.pc.test.WebSpringApp.dto.SedeDTO;
import it.pc.test.WebSpringApp.enums.TipoCategoria;
import org.springframework.beans.factory.annotation.Value;

/**
 * Projection to get basic info of produttore and its related tables
 */
public interface ProduttoreBasicInfo {

    // Produttore
    @Value("#{target.id_produttore}")
    Integer getProduttoreId();

    @Value("#{target.nome_produttore}")
    String getProduttoreNome();

    @Value("#{T(it.pc.test.WebSpringApp.enums.TipoCategoria).getEnumByValue(target.tipo_categoria_id)}")
    TipoCategoria getTipoCategoria();

    // Sede
    @Value("#{T(it.pc.test.WebSpringApp.dto.SedeDTO).ofMinimal(target.sede_id, target.sede_indirizzo, target.sede_nazione)}")
    SedeDTO getSedeProduttore();
    // Partner
    @Value("#{target.nome_partner}")
    String getNomePartner();

    // Sede Partner
    @Value("#{target.indirizzo_partner}")
    String getIndirizzoPartner();



}
