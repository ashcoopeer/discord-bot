package com.ashcoopeer.discord.messages;

import com.ashcoopeer.discord.model.Question;
import org.javacord.api.entity.channel.PrivateChannel;
import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.component.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomQuestionMessageService {

    public void sendMessage(PrivateChannel channel, Question question) {
        new MessageBuilder()
                .setContent("Select an option of this list!")
                .addComponents(
                        ActionRow.of(TextInput.create(TextInputStyle.PARAGRAPH, "inputE", "inputE", 5, 10))
                )
                .send(channel);
    }
}
