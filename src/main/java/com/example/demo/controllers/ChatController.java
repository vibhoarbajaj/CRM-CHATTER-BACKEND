package com.example.demo.controllers;

import com.example.demo.dto.request.ChatRequest;
import com.example.demo.dto.response.ChatResponse;
import com.example.demo.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/fetchAllChats")
    public List<ChatResponse> getAllChats(
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSz", defaultValue = "5", required = false) Integer pageSz
    ) {
        return chatService.getAllChats(pageNum, pageSz);
    }

    @GetMapping("/fetchChat/{id}")
    public ChatResponse getChatByChatId(
            @PathVariable("id") Long id
    ) {
        return chatService.getChatByChatId(id);
    }

    @GetMapping("/fetchChatsByUserId/{userId}")
    public List<ChatResponse> getAllChats(
            @PathVariable("userId") Long userId) {
        return chatService.getChatsByUsersId(userId);
    }

    @PostMapping("/addNewChat")
    public ChatResponse addNewChats(@RequestBody ChatRequest chatRequest) {
        return chatService.addNewChat(chatRequest);
    }

    // add and remove in the chat can only be used if it is a group
    @PutMapping("/addUserInChat/{chatId}")
    // since there can be multiple person that can be added or removed hence send a request body or list of ids to be added or removed
    // ,but we also have to provide a chatId which we need to update
    public ChatResponse addUserInChat(@RequestBody ChatRequest chatRequest,
                                      @PathVariable Long chatId) {
        return chatService.addUserInChat(chatRequest, chatId);
    }

    @PutMapping("/removeUserFromChat/{chatId}")
    public ChatResponse removeUserFromChat(@RequestBody ChatRequest chatRequest,
                                           @PathVariable Long chatId) {
        return chatService.removeUserFromChat(chatRequest, chatId);
    }
}
