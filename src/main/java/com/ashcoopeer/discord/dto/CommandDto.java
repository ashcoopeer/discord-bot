package com.ashcoopeer.discord.dto;

import com.ashcoopeer.discord.messages.enums.CommandTypeEnum;
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
