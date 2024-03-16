package com.chat.server.infrastructure.utils.log;

public class LogBuilder {
   
    private static final String HEADER_PREFIX = " => ";
    private static final String ROW_PREFIX = "\n - ";
    private static final String ROW_SEPARATOR = ": ";

    private StringBuilder stringBuilder;

    private LogBuilder() {
        this.stringBuilder = new StringBuilder();
    }

    public static LogBuilder of() {
        return new LogBuilder();
    }

    public LogBuilder header(String header) {
        stringBuilder.append(HEADER_PREFIX);
        stringBuilder.append(header);
        return this;
    }

    public LogBuilder row(String description, Object value) {
        stringBuilder.append(ROW_PREFIX);
        stringBuilder.append(description);
        stringBuilder.append(ROW_SEPARATOR);
        stringBuilder.append(value);
        return this;
    }

    public String build() {
        return stringBuilder.toString();
    }
}
