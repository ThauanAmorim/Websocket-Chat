package com.chat.server.presentation.dto.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1l;

    private transient T data;
    private List<String> errors;
 
    public Response() {}

    public Response(T data) {
        super();
        this.data = data;
        this.errors = new ArrayList<>();
    }

    public static <T> Response<T> of(T data) {
        return new Response<>(data);
    }
    
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        getErrors().add(error);
    }
}
