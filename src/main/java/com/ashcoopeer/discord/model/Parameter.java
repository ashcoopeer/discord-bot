package com.ashcoopeer.discord.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Parameter implements Serializable {
    @Id
    @TableGenerator(
            name="ParameterGen",
            table="SEQUENCE",
            pkColumnName="SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue="P_IDENTIFIER",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ParameterGen")
    @Column(name = "P_IDENTIFIER")
    private Long identifier;

    @Column(name = "P_NAME")
    private String name;

    @Column(name = "P_REGEXPRE")
    private String regularExpression;

    @Column(name = "P_ORDER")
    private Long order;

    @Column(name = "C_IDENTIFIER")
    private Long commandIdentifier;
}
