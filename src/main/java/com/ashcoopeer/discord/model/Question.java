package com.ashcoopeer.discord.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Question {
    @Id
    @TableGenerator(
            name="QuestionGen",
            table="SEQUENCE",
            pkColumnName="SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue="Q_IDENTIFIER",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "QuestionGen")
    @Column(name = "Q_IDENTIFIER")
    private Long identifier;

    @Column(name = "Q_QUESTION")
    private String question;
}
