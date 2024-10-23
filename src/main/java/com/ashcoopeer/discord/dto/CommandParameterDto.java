package com.ashcoopeer.discord.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandParameterDto {
    private String name;
    private String value;
    private String regex;
    private int order;
}
