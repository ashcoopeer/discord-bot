package com.ashcoopeer.discord.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class QuestionResponse {

    @Id
    @TableGenerator(
            name="QuestionResponseGen",
            table="SEQUENCE",
            pkColumnName="SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue="R_IDENTIFIER",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "QuestionResponseGen")
    @Column(name = "R_IDENTIFIER")
    private Long identifier;

    @Column(name = "R_USERNAME")
    private String username;

    @Column(name = "Q_IDENTIFIER")
    private Long questionIdentifier;

    @Column(name = "Q_RESPONSE")
    private Long questionResponse;
}
