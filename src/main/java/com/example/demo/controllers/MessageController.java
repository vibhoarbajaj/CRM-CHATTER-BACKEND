package com.example.demo.controllers;

import com.example.demo.dto.request.MessageRequest;
import com.example.demo.dto.response.MessageResponse;
import com.example.demo.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/message")
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/fetchAllMessages")
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/fetchMessagesByChatId/{chatId}")
    public List<MessageResponse> getAllMessagesByChatId(
            @PathVariable(value = "chatId") Long chatId,
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(value = "pageSz", defaultValue = "5", required = false) Integer pageSz
    ) {
        return messageService.getAllMessagesByChatId(chatId, pageNum, pageSz);
    }

    @PostMapping("/addNewMessage")
    public MessageResponse addNewMessage(
            @RequestBody MessageRequest messageRequest
    ) {
        return messageService.addNewMessage(messageRequest);
    }

    @PutMapping("/updateMessage/{msgId}")
    public MessageResponse updateMessage(@PathVariable("msgId") Long msgId,
                                         @RequestBody MessageRequest messageRequest) {
        return messageService.updateMessage(msgId, messageRequest);
    }

    @DeleteMapping("/deleteMessage/{msgId}")
    public ResponseEntity<String> deleteMessage(@PathVariable("msgId") Long msgId) {
        messageService.deleteMessage(msgId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Message deleted successfully!!");
    }
}
