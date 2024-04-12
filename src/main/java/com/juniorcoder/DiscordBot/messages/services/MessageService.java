package com.juniorcoder.DiscordBot.messages.services;

import com.juniorcoder.DiscordBot.dto.CommandDto;
import com.juniorcoder.DiscordBot.dto.CommandParameterDto;
import com.juniorcoder.DiscordBot.exceptions.CommandNotFoundException;
import com.juniorcoder.DiscordBot.exceptions.CommandParameterInvalidException;
import com.juniorcoder.DiscordBot.services.CommandService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MessageService {

    private final CommandService commandService;

    public MessageService(CommandService commandService) {
        this.commandService = commandService;
    }

    public void executeCommand(String[] commandAndParameters) throws Exception {
        String commandName = commandAndParameters[0];
        String value;
        Pattern pattern;
        Matcher matcher;

        CommandDto commandDto = this.commandService.findByName(commandName);
        if (commandDto == null) {
            throw new CommandNotFoundException(String.format("Command %s not found", commandName));
        }

        //valid parameters
        for (CommandParameterDto commandParameterDto : commandDto.getCommandParameterDtoList()) {
            if (commandParameterDto.getRegex() != null) {
                value = commandAndParameters[commandParameterDto.getOrder()];

                pattern = Pattern.compile(commandParameterDto.getRegex());
                matcher = pattern.matcher(value);

                if (!matcher.find()) {
                    throw new CommandParameterInvalidException(String.format("Parameter %s with value %s is not valid", commandParameterDto.getName(), value));
                }
            }
        }

        //Execute Message


    }
}
