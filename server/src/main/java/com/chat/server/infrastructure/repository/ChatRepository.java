package com.chat.server.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chat.server.domain.model.chat.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    
}
