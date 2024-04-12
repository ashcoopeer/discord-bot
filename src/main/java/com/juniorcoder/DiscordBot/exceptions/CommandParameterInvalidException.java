package com.juniorcoder.DiscordBot.exceptions;

public class CommandParameterInvalidException extends RuntimeException {
    public CommandParameterInvalidException(String message) {
        super(message);
    }
}
