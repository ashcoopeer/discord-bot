package com.ashcoopeer.discord.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Command implements Serializable {

    @Id
    @TableGenerator(
            name="CommandGen",
            table="SEQUENCE",
            pkColumnName="SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue="C_IDENTIFIER",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "CommandGen")
    @Column(name = "C_IDENTIFIER")
    private Long identifier;

    @Column(name = "C_NAME")
    private String name;

}
