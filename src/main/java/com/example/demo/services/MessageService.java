package com.example.demo.services;

import com.example.demo.dto.request.MessageRequest;
import com.example.demo.dto.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<MessageResponse> getAllMessages();

    List<MessageResponse> getAllMessagesByChatId(Long chatId, Integer pageNum, Integer pageSz);

    ResponseEntity<?> addNewMessage(MessageRequest messageRequest);

    ResponseEntity<?> updateMessage(Long msgId, MessageRequest messageRequest);

    ResponseEntity<?> deleteMessage(Long msgId);
}
