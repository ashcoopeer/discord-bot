package com.ashcoopeer.discord.dao;

import com.ashcoopeer.discord.model.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandParameterRepository extends JpaRepository<Parameter, Long> {

    @Query("select c from Parameter c where c.commandIdentifier = :commandId")
    List<Parameter> findByCommandId(@Param("commandId") Long commandId);
}
