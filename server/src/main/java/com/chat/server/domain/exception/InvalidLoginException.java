package com.chat.server.domain.exception;

public class InvalidLoginException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Login %s is invalid. Error: %s";

    public static final String EMPTY_LOGIN = "Is empty";
    public static final String LOGIN_ALREADY_USED = "Already in use";

    public InvalidLoginException(String login, String error) {
        super(buildMessage(login, error));
    }

    private static String buildMessage(String login, String error) {
        return String.format(DEFAULT_MESSAGE, login, error);
    }
    
}
