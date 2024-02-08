package com.example.demo.services;

import com.example.demo.dto.request.ChatRequest;
import com.example.demo.dto.response.ChatResponse;

import java.util.List;


public interface ChatService {
    List<ChatResponse> getAllChats(Integer pageNum, Integer pageSz);

    ChatResponse getChatByChatId(Long id);

    List<ChatResponse> getChatsByUsersId(Long userId);

    ChatResponse addNewChat(ChatRequest chatRequest);

    ChatResponse addUserInChat(ChatRequest chatRequest, Long chatId);

    ChatResponse removeUserFromChat(ChatRequest chatRequest, Long chatId);
}
