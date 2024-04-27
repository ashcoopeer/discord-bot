package com.juniorcoder.DiscordBot.messages.listeners;

import com.juniorcoder.DiscordBot.messages.services.CommandDelegateService;
import com.juniorcoder.DiscordBot.utils.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MainCommandMessageListener implements MessageCreateListener {

    private final CommandDelegateService commandDelegateService;

    public MainCommandMessageListener(CommandDelegateService commandDelegateService) {
        this.commandDelegateService = commandDelegateService;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String[] commandAndParameters = MessageUtils.getCommandAndParameters(messageCreateEvent.getMessageContent());

        if (commandAndParameters.length > 0) {
            try {
                this.commandDelegateService.delegateCommand(commandAndParameters, messageCreateEvent);
            } catch (Exception e) {

            }
        }
    }
}
