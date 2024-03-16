package com.chat.server.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chat.server.domain.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
   
    @Query("SELECT u FROM User u WHERE u.login = :login")
    public Optional<User> findByLogin(@Param("login") String login);

}
