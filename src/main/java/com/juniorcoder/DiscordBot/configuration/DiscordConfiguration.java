package com.juniorcoder.DiscordBot.configuration;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DiscordConfiguration {



    @Bean
    public DiscordApi discordApi() {
        DiscordApi discordApi = new DiscordApiBuilder()
                .setToken(System.getProperty("discord.token"))
                .login()
                .join();

        //discordApi.add

        return discordApi;
    }
}
