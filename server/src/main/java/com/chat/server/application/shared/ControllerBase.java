package com.chat.server.application.shared;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import com.chat.server.presentation.dto.shared.Response;

@Component
public abstract class ControllerBase {
    
    protected final ModelMapper modelMapper;

    protected ControllerBase(final ModelMapper modelMapper) {
        this.modelMapper =  modelMapper;
    }

    protected <T> ResponseEntity<Response<T>> createResponse(T data, @NonNull HttpStatus httpStatus) {
        Response<T> response = Response.of(data);

        return new ResponseEntity<>(response, httpStatus);
    }

    protected <T> ResponseEntity<Response<T>> createResponse(T data) {
        return createResponse(data, HttpStatus.OK);
    }

    protected <T> ResponseEntity<Response<T>> createResponseError(Exception exception) {
        Response<T> response =  Response.of(null);
        response.addError(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
