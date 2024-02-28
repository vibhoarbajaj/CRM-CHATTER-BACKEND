package com.example.demo.controllers;

import com.example.demo.dto.request.MessageRequest;
import com.example.demo.dto.response.MessageResponse;
import com.example.demo.model.Chat;
import com.example.demo.model.Message;
import com.example.demo.model.Person;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.services.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@Controller
@RequestMapping(path = "/api/message")
@CrossOrigin(origins = "*")
public class MessageController {
    private final MessageService messageService;
    private final ChatRepository chatRepository;

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    public MessageController(MessageService messageService, ChatRepository chatRepository) {
        this.messageService = messageService;
        this.chatRepository = chatRepository;
    }

    @GetMapping("/fetchAllMessages")
    public List<MessageResponse> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/fetchMessagesByChatId/{chatId}")
    public List<MessageResponse> getAllMessagesByChatId(
            @PathVariable(value = "chatId") Long chatId,
            @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
            @RequestParam(value = "pageSz", defaultValue = "5", required = false) Integer pageSz
    ) {
        return messageService.getAllMessagesByChatId(chatId, pageNum, pageSz);
    }
  @MessageMapping("/addNewSock/{chatId}")//api/message/
 // @SendTo("/chatroom")
  public ResponseEntity<?> getMsgByChat(@DestinationVariable Long chatId,  @Payload MessageRequest msg){
     //simpMessagingTemplate.convertAndSend("/chatroom/"+chatId,msg.getMessageBody());
           ResponseEntity<?> addedMessage = messageService.addNewMessage(msg);
          simpMessagingTemplate.convertAndSend("/chatroom/" + chatId, addedMessage);
      return addedMessage;
    }

//    @PostMapping("/addNewMessage")
//    public ResponseEntity<?> addNewMessage(
//            @RequestBody MessageRequest messageRequest
//    ) {
//        return messageService.addNewMessage(messageRequest);
//    }

    @PatchMapping ("/updateMessage/{msgId}")
    public ResponseEntity<?> updateMessage(@PathVariable("msgId") Long msgId,
                                         @RequestBody MessageRequest messageRequest) {
        return messageService.updateMessage(msgId, messageRequest);
    }

    @DeleteMapping("/deleteMessage/{msgId}")
    public ResponseEntity<?> deleteMessage(@PathVariable("msgId") Long msgId) {
        messageService.deleteMessage(msgId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Message deleted successfully!!");
    }
}
