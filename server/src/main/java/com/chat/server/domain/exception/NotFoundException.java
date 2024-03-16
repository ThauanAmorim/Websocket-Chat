package com.chat.server.domain.exception;

public class NotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "%s was not found";

    public NotFoundException(String entityName) {
        super(buildMessage(entityName));
    }

    private static String buildMessage(String entityName) {
        return String.format(DEFAULT_MESSAGE, entityName);
    }
    
}
