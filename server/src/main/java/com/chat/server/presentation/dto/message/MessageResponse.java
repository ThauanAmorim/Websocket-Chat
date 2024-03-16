package com.chat.server.presentation.dto.message;

import java.time.LocalDateTime;

import com.chat.server.presentation.dto.user.UserResponse;

public class MessageResponse {
   
    private Long id;

    private String content;

    private UserResponse sender;

    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public UserResponse getSender() {
        return sender;
    }

    public void setSender(UserResponse user) {
        this.sender = user;
    }

}
