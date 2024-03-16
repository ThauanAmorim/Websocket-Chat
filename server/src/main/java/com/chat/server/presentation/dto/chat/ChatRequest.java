package com.chat.server.presentation.dto.chat;

import java.util.List;

public class ChatRequest {
   
    private Long id;

    private String name;

    private List<String> logins;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getLogins() {
        return logins;
    }

    public void setLogins(List<String> logins) {
        this.logins = logins;
    }

    public String getName() {
        return name;
    }

    public void setName(String login) {
        this.name = login;
    }

}
