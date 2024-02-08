package com.example.demo.services;

import com.example.demo.dto.request.MessageRequest;
import com.example.demo.dto.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {
    List<MessageResponse> getAllMessages();

    List<MessageResponse> getAllMessagesByChatId(Long chatId, Integer pageNum, Integer pageSz);

    MessageResponse addNewMessage(MessageRequest messageRequest);

    MessageResponse updateMessage(Long msgId, MessageRequest messageRequest);

    MessageResponse deleteMessage(Long msgId);
}
