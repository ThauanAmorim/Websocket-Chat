package com.chat.server.application.user.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.server.application.shared.ControllerBase;
import com.chat.server.application.user.service.UserService;
import com.chat.server.domain.model.user.User;
import com.chat.server.presentation.dto.shared.Response;
import com.chat.server.presentation.dto.user.UserRequest;
import com.chat.server.presentation.dto.user.UserResponse;

@RestController
@RequestMapping("/api/user")
public class UserController extends ControllerBase {

    private final UserService userService;

    public UserController(
            final ModelMapper modelMapper,
            final UserService userService
    ) {
        super(modelMapper);
        this.userService = userService;
    }
    
    @PostMapping
    public ResponseEntity<Response<UserResponse>> create(@RequestBody UserRequest dto) {
        User user = modelMapper.map(dto, User.class);
        try {
            user = userService.create(user);
            UserResponse response = modelMapper.map(user, UserResponse.class);

            return createResponse(response, HttpStatus.CREATED);
        } catch (Exception exception) {
            return createResponseError(exception);        
        }
    }
}
