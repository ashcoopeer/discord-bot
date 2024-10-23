package com.ashcoopeer.discord.messages;

import org.javacord.api.event.message.MessageCreateEvent;

public class TextMessageService implements MessageService<String> {
    @Override
    public void sendMessage(MessageCreateEvent messageCreateEvent, String message) {

        if (messageCreateEvent.getChannel() != null) {
            messageCreateEvent.getChannel().sendMessage(message);
        }
    }
}
