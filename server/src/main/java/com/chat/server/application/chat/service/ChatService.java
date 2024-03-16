package com.chat.server.application.chat.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chat.server.application.user.service.UserService;
import com.chat.server.domain.exception.InvalidChatException;
import com.chat.server.domain.exception.NotFoundException;
import com.chat.server.domain.model.chat.Chat;
import com.chat.server.domain.model.message.Message;
import com.chat.server.domain.model.user.User;
import com.chat.server.infrastructure.repository.ChatRepository;
import com.chat.server.infrastructure.utils.CollectionsUtils;
import com.chat.server.presentation.dto.chat.ChatRequest;
import com.chat.server.presentation.dto.message.MessageRequest;

@Service
public class ChatService {
    
    private final ChatService chatServiceProxy;
    private final UserService userService;
    private final ChatRepository chatRepository;

    public ChatService(
            @Lazy final ChatService chatService,
            final ChatRepository chatRepository,
            final UserService userService
    ) {
        this.chatServiceProxy = chatService;
        this.chatRepository = chatRepository;
        this.userService = userService;
    }

    @Transactional
    public Chat create(ChatRequest chatDto) {
        validate(chatDto);

        Chat chat = new Chat();
        chat.setName(chatDto.getName());
        chat.create();
        defineUsers(chat, chatDto.getLogins());

        return chatRepository.save(chat);
    }

    @Transactional
    public Message createMessage(MessageRequest messageRequest) {
        Chat chat = chatServiceProxy.findById(messageRequest.getChatId());
        User sender = userService.findById(messageRequest.getSenderId());

        Message message = Message.of(messageRequest.getContent(), chat, sender);
        chat.addMessage(message);
        chatRepository.save(chat);

        return message;
    }

    @Transactional(readOnly = true)
    public Chat findById(long id) {
        Optional<Chat> chatOp = chatRepository.findById(id);
        return chatOp.orElseThrow(() -> new NotFoundException(Chat.class.getSimpleName()));
    }

    public List<Chat> findAll() {
        return chatRepository.findAll();
    }

    protected void defineUsers(Chat chat, List<String> logins) {
        List<User> users = logins.stream()
                .map(userService::findByLogin)
                .toList();
        
        chat.setUsers(users);
        users.forEach(user -> user.getChats().add(chat));
    }

    protected void validate(ChatRequest chatDto) {
        //TODO verificar se o login principal não está na lista de logins enviados; 
        if (CollectionsUtils.isEmpty(chatDto.getLogins())) {
            throw new InvalidChatException(InvalidChatException.EMPTY_LOGIN);
        }

        if (chatDto.getLogins().size() < 2) {
            throw new InvalidChatException(InvalidChatException.INVALID_SIZE);
        }
    }    

}
