package com.ashcoopeer.discord.messages.services;

import com.ashcoopeer.discord.dao.QuestionRepository;
import com.ashcoopeer.discord.dto.CommandDto;
import com.ashcoopeer.discord.dto.CommandParameterDto;
import com.ashcoopeer.discord.exceptions.CommandNotFoundException;
import com.ashcoopeer.discord.exceptions.CommandParameterInvalidException;
import com.ashcoopeer.discord.exceptions.CommandTypeNotSupport;
import com.ashcoopeer.discord.messages.CustomQuestionMessageService;
import com.ashcoopeer.discord.messages.MessageService;
import com.ashcoopeer.discord.messages.TextMessageService;
import com.ashcoopeer.discord.model.Question;
import com.ashcoopeer.discord.services.CommandService;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommandDelegateService {

    private final CommandService commandService;
    private final QuestionRepository questionRepository;
    private final CustomQuestionMessageService customQuestionMessageService;

    public CommandDelegateService(CommandService commandService, QuestionRepository questionRepository, CustomQuestionMessageService customQuestionMessageService) {
        this.commandService = commandService;
        this.questionRepository = questionRepository;
        this.customQuestionMessageService = customQuestionMessageService;
    }

    public void delegateCommand(String[] commandAndParameters, MessageCreateEvent messageCreateEvent) throws Exception {
        String commandName = commandAndParameters[0];
        String value;
        Pattern pattern;
        Matcher matcher;
        MessageService messageService;

        if ("coopeer".equals(commandName)) {
            ashDelegateCommand(messageCreateEvent);
            return;
        }

        CommandDto commandDto = this.commandService.findByName(commandName);
        if (commandDto == null) {
            throw new CommandNotFoundException(String.format("Command %s not found", commandName));
        }

        //valid parameters
        for (CommandParameterDto commandParameterDto : commandDto.getCommandParameterDtoList()) {
            if (commandParameterDto.getRegex() != null) {
                value = commandAndParameters[commandParameterDto.getOrder().intValue()];

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

    public void ashDelegateCommand(MessageCreateEvent messageCreateEvent) {
        System.out.println("ASh command");

        Optional<PrivateChannel> privateChannelOptional = messageCreateEvent.getPrivateChannel();
        if (privateChannelOptional.isPresent()) {
            List<Question> questionList = questionRepository.findAll();
            for (Question question : questionList) {
                System.out.println(question.getQuestion());

                customQuestionMessageService.sendMessage(privateChannelOptional.get(), question);
            }
        }
    }
}
