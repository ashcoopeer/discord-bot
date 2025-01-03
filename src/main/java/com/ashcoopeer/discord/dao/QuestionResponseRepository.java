package com.ashcoopeer.discord.dao;

import com.ashcoopeer.discord.model.QuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionResponseRepository extends JpaRepository<QuestionResponse, Long> {
}
