package com.chat.server.infrastructure.websocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chat.server.application.chat.service.ChatService;
import com.chat.server.domain.exception.NotFoundException;
import com.chat.server.domain.model.chat.Chat;
import com.chat.server.domain.model.message.Message;
import com.chat.server.infrastructure.service.JsonService;
import com.chat.server.infrastructure.utils.QueryUtils;
import com.chat.server.presentation.dto.message.MessageRequest;
import com.chat.server.presentation.dto.message.MessageResponse;

@Component
@SuppressWarnings("null")
public class ServerWebSocketHandler extends TextWebSocketHandler {

    private static final String CHAT_ID = "chatId";

    private final Map<String, List<WebSocketSession>> sessions;

    private final ChatService chatService;
    private final JsonService jsonService;
    private final ModelMapper modelMapper;

    public ServerWebSocketHandler(
            final ChatService chatService,
            final JsonService jsonService
    ) {
        this.chatService = chatService;
        this.jsonService = jsonService;
        this.modelMapper = new ModelMapper();
        this.sessions = new HashMap<>();
    }

    @Override
    @Transactional
    public void afterConnectionEstablished(WebSocketSession session) {
        Map<String, String> query = QueryUtils.getMapFromString(session.getUri().getQuery());
        String chatId = query.get(CHAT_ID);
        
        Chat chat = chatService.findById(Long.parseLong(chatId));
        if (Objects.isNull(chat)) {
            throw new NotFoundException(Chat.class.getSimpleName());
        }

        List<WebSocketSession> sessionsList = sessions.get(chatId);
        if (Objects.nonNull(sessionsList)) {
            sessionsList.add(session);
        } else {
            sessionsList = new ArrayList<>();
            sessionsList.add(session);
    
            sessions.put(chatId, sessionsList);
        }

        loadMessages(chat);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        Map<String, String> query = QueryUtils.getMapFromString(session.getUri().getQuery());
        String chatId = query.get(CHAT_ID);

        List<WebSocketSession> sessionSaved = sessions.get(chatId);
        if (Objects.isNull(sessionSaved)) {
            return;
        }

        sessionSaved.remove(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
        String request = textMessage.getPayload();

        MessageRequest messageRequest = jsonService.convertFromString(request, MessageRequest.class);
        Message message = chatService.createMessage(messageRequest);
        sendMessage(message);
    }

    protected void sendMessage(Message message) {
        MessageResponse messageResponse = modelMapper.map(message, MessageResponse.class);
        TextMessage textMessage = new TextMessage(jsonService.convertToString(messageResponse));

        String chatId = message.getChat().getId().toString();
        sessions.get(chatId).parallelStream().forEach(activeSession -> {
            try {
                activeSession.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadMessages(Chat chat) {
        chat.getMessages()
                .forEach(this::sendMessage);
    }

}