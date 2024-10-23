package com.ashcoopeer.discord.exceptions;

public class CommandParameterInvalidException extends RuntimeException {
    public CommandParameterInvalidException(String message) {
        super(message);
    }
}
