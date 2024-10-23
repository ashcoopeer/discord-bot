package com.ashcoopeer.discord.messages.services;

import com.ashcoopeer.discord.dto.CommandDto;
import com.ashcoopeer.discord.dto.CommandParameterDto;
import com.ashcoopeer.discord.exceptions.CommandNotFoundException;
import com.ashcoopeer.discord.exceptions.CommandParameterInvalidException;
import com.ashcoopeer.discord.exceptions.CommandTypeNotSupport;
import com.ashcoopeer.discord.messages.MessageService;
import com.ashcoopeer.discord.messages.TextMessageService;
import com.ashcoopeer.discord.services.CommandService;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommandDelegateService {

    private final CommandService commandService;

    public CommandDelegateService(CommandService commandService) {
        this.commandService = commandService;
    }

    public void delegateCommand(String[] commandAndParameters, MessageCreateEvent messageCreateEvent) throws Exception {
        String commandName = commandAndParameters[0];
        String value;
        Pattern pattern;
        Matcher matcher;
        MessageService messageService;

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

        switch (commandDto.getType()) {
            case TEXT -> {
                messageService = new TextMessageService();
            }
            default -> {
                throw new CommandTypeNotSupport();
            }
        }

        //Execute Message
        messageService.sendMessage(messageCreateEvent, commandDto.getMessage());


    }
}
