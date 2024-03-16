package com.chat.server.domain.exception;

public class InvalidChatException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Chat is invalid. Error: %s";

    public static final String EMPTY_LOGIN = "No login was informed";
    public static final String INVALID_SIZE = "At least two users is required";

    public InvalidChatException(String error) {
        super(buildMessage(error));
    }

    private static String buildMessage(String error) {
        return String.format(DEFAULT_MESSAGE, error);
    }
    
}
