package com.juniorcoder.DiscordBot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class CommandParameter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "REGEX")
    private String regex;

    @Column(name = "ORDER")
    private int order;

    @ManyToOne
    @JoinColumn(name = "CID", referencedColumnName = "ID")
    private Command command;
}
