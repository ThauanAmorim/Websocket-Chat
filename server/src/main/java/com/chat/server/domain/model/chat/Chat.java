package com.chat.server.domain.model.chat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.chat.server.domain.model.message.Message;
import com.chat.server.domain.model.user.User;
import com.chat.server.infrastructure.utils.CollectionsUtils;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_chat")
public class Chat implements Serializable {
   
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "chats")
    private List<User> users;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages;

    private LocalDateTime createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public void create() {
        initiateUsers();
        initiateMessages();
        setCreateDate(LocalDateTime.now());
    }

    public void initiateUsers() {
        if (CollectionsUtils.notEmpty(getUsers())) {
            return;
        }
        setUsers(new ArrayList<>());
    }

    public void initiateMessages() {
        if (CollectionsUtils.notEmpty(getMessages())) {
            return;
        }
        setMessages(new ArrayList<>());
    }

    public void addMessage(Message message) {
        getMessages().add(message);
    }
}
