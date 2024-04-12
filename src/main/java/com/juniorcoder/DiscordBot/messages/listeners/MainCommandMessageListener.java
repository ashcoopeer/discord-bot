package com.juniorcoder.DiscordBot.messages.listeners;

import com.juniorcoder.DiscordBot.messages.services.MessageService;
import com.juniorcoder.DiscordBot.utils.MessageUtils;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class MainCommandMessageListener implements MessageCreateListener {

    private final MessageService messageService;

    public MainCommandMessageListener(MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void onMessageCreate(MessageCreateEvent messageCreateEvent) {
        String[] commandAndParameters = MessageUtils.getCommandAndParameters(messageCreateEvent.getMessageContent());

        if (commandAndParameters.length > 0) {
            try {
                this.messageService.executeCommand(commandAndParameters);
            } catch (Exception e) {

            }
        }
    }
}
