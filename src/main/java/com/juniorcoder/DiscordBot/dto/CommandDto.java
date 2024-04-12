package com.juniorcoder.DiscordBot.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommandDto {

    private Long id;
    private String commandName;
    private String message;
    private List<CommandParameterDto> commandParameterDtoList;
}
