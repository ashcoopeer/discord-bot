package com.ashcoopeer.discord.services;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<Entity,Dto, PK> {

    protected final JpaRepository<Entity, PK> repository;

    public CrudService(JpaRepository<Entity, PK> repository) {
        this.repository = repository;
    }

    public Dto save(Dto dto) {
        Entity entity = this.repository.save(toEntity(dto));
        return toDto(entity);
    }

    public abstract Entity toEntity(Dto dto);

    public abstract Dto toDto(Entity dto);
}
