package com.chat.server.presentation.dto.chat;

import java.util.List;

import com.chat.server.presentation.dto.message.MessageResponse;
import com.chat.server.presentation.dto.user.UserResponse;

public class ChatResponse {
   
    private Long id;

    private String name;

    private List<UserResponse> users;

    private List<MessageResponse> messages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
