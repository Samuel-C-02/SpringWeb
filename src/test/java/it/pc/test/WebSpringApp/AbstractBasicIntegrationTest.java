package it.pc.test.WebSpringApp;

import it.pc.test.WebSpringApp.dto.AbstractBaseDTO;
import it.pc.test.WebSpringApp.entity.AbstractBaseEntity;
import it.pc.test.WebSpringApp.mapper.IMapperBase;
import it.pc.test.WebSpringApp.service.AbstractBaseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Class for basic integration test that don't need complex logic
 *
 * @param <DTO>
 * @param <ENTITY>
 * @param <ID>
 * @param <MAPPER>
 * @param <REPOSITORY>
 * @param <SERVICE>
 */

public abstract class AbstractBasicIntegrationTest<
        DTO extends AbstractBaseDTO<ID>,
        ENTITY extends AbstractBaseEntity<ID>,
        ID,
        MAPPER extends IMapperBase<ENTITY, DTO>,
        REPOSITORY extends JpaRepository<ENTITY, ID>,
        SERVICE extends AbstractBaseService<ENTITY, DTO, ID, MAPPER, REPOSITORY>> {

    abstract SERVICE getService();

    abstract ID getExistingId();

    @Test
    void findAllByIdTest() {
        DTO dtoById = getService().findByIdBase(getExistingId());
        Assertions.assertNotNull(dtoById);
        Assertions.assertEquals(getExistingId(), dtoById.getId());
    }


}
