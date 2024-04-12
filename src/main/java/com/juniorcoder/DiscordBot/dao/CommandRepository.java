package com.juniorcoder.DiscordBot.dao;

import com.juniorcoder.DiscordBot.model.Command;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandRepository extends JpaRepository<Command, Long> {

    Optional<Command> findByName(String name);
}
