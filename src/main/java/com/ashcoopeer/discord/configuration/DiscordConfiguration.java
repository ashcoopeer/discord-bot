package com.ashcoopeer.discord.configuration;

import com.ashcoopeer.discord.messages.listeners.MainCommandMessageListener;
import com.ashcoopeer.discord.messages.services.CommandDelegateService;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfiguration {



    @Bean
    public DiscordApi discordApi(CommandDelegateService commandDelegateService) {
        DiscordApi discordApi = new DiscordApiBuilder()
                .setToken(System.getProperty("discord.token"))
                .addIntents(Intent.MESSAGE_CONTENT)
                .login()
                .join();

        discordApi.addMessageCreateListener(new MainCommandMessageListener(commandDelegateService));

        return discordApi;
    }
}
