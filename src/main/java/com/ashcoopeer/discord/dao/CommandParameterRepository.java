package com.ashcoopeer.discord.dao;

import com.ashcoopeer.discord.model.CommandParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandParameterRepository extends JpaRepository<CommandParameter, Long> {

    @Query("select c from CommandParameter c where c.command.id = :commandId")
    List<CommandParameter> findByCommandId(@Param("commandId") Long commandId);
}
