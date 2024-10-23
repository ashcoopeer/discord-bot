package com.ashcoopeer.discord.messages;

import org.javacord.api.event.message.MessageCreateEvent;

public interface MessageService<T> {
    void sendMessage(MessageCreateEvent channel, T message);
}
