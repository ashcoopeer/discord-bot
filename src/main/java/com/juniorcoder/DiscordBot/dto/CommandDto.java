package com.juniorcoder.DiscordBot.dto;

import com.juniorcoder.DiscordBot.messages.enums.CommandTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class CommandDto {

    private Long id;
    private String commandName;
    private String message;
    private CommandTypeEnum type;
    private List<CommandParameterDto> commandParameterDtoList;
}
