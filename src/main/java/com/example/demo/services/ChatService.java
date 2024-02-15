package com.example.demo.services;

import com.example.demo.dto.request.ChatRequest;
import com.example.demo.dto.response.ChatResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ChatService {
    List<ChatResponse> getAllChats(Integer pageNum, Integer pageSz);

    ChatResponse getChatByChatId(Long id);

    ResponseEntity<?> getChatsByUsersId(Long userId);
    ResponseEntity<?> getGroupsById(Long id);

    ResponseEntity<?> addNewChat(ChatRequest chatRequest);

    ResponseEntity<?> addUserInChat(ChatRequest chatRequest, Long chatId);

    ResponseEntity<?> removeUserFromChat(ChatRequest chatRequest, Long chatId);
}
