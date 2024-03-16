package com.chat.server.application.user.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chat.server.domain.exception.InvalidLoginException;
import com.chat.server.domain.exception.NotFoundException;
import com.chat.server.domain.model.user.User;
import com.chat.server.infrastructure.repository.UserRepository;
import com.chat.server.infrastructure.utils.StringUtils;

@Service
public class UserService {
   
    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User create(User user) {
        validateLogin(user);

        user.create();
        userRepository.save(user);

        return user;
    }

    @Transactional(readOnly = true)
    public User findByLogin(String login) {
        Optional<User> userOp = userRepository.findByLogin(login);
        return userOp.orElseThrow(() -> new NotFoundException(User.class.getSimpleName()));
    }
    
    @Transactional(readOnly = true)
    public User findById(long id) {
        Optional<User> userOp = userRepository.findById(id);
        return userOp.orElseThrow(() -> new NotFoundException(User.class.getSimpleName()));
    }

    protected void validateLogin(User user) {
        String login = user.getLogin();
        if (StringUtils.isBlank(login)) {
            throw new InvalidLoginException(login, InvalidLoginException.EMPTY_LOGIN) ;
        }

        Optional<User> userAuxOp = userRepository.findByLogin(login);
        if (userAuxOp.isPresent()) {
            throw new InvalidLoginException(login, InvalidLoginException.LOGIN_ALREADY_USED);
        }
    }
}
