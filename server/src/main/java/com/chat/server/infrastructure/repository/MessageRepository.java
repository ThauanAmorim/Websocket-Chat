package com.chat.server.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.server.domain.model.message.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    
}
