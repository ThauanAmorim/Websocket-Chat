package com.chat.server.application.chat.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.server.application.chat.service.ChatService;
import com.chat.server.application.shared.ControllerBase;
import com.chat.server.domain.model.chat.Chat;
import com.chat.server.presentation.dto.chat.ChatMinResponse;
import com.chat.server.presentation.dto.chat.ChatRequest;
import com.chat.server.presentation.dto.chat.ChatResponse;
import com.chat.server.presentation.dto.shared.Response;

@RestController
@RequestMapping("/api/chat")
public class ChatController extends ControllerBase {
    
    private final ChatService chatService;

    public ChatController(
            final ModelMapper modelMapper,
            final ChatService chatService
    ) {
        super(modelMapper);
        this.chatService = chatService;
    }

    @PostMapping
    public ResponseEntity<Response<ChatResponse>> create(@RequestBody ChatRequest chatDto) {
        try {
            Chat chat = chatService.create(chatDto);
            ChatResponse response = modelMapper.map(chat, ChatResponse.class);

            return createResponse(response, HttpStatus.CREATED);
        } catch (Exception exception) {
            return createResponseError(exception);
        } 
    }

    @GetMapping
    public ResponseEntity<Response<List<ChatMinResponse>>> findAll() {
        try {
            List<Chat> chats = chatService.findAll();
            List<ChatMinResponse> chatsDto = chats.stream()
                    .map(chat -> modelMapper.map(chat, ChatMinResponse.class))
                    .toList();

            return createResponse(chatsDto);
        } catch (Exception exception) {
            return createResponseError(exception);
        }
    }

}
