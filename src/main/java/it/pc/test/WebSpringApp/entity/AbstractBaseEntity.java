package it.pc.test.WebSpringApp.entity;

import lombok.Data;

@Data
public abstract class AbstractBaseEntity<EntityIdType> {
    private EntityIdType id;
}
