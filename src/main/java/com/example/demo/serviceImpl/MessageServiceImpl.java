package com.example.demo.serviceImpl;

import com.example.demo.dto.request.MessageRequest;
import com.example.demo.dto.response.MessageResponse;
import com.example.demo.model.Message;
import com.example.demo.model.Person;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.MessageRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final PersonRepository personRepository;


    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository, PersonRepository personRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.personRepository = personRepository;
    }

    private List<MessageResponse> convertMsgListToMsgResponseList(List<Message> messageList) {
        List<MessageResponse> messageResponseList = new ArrayList<>();

        for (Message msg : messageList) {
            MessageResponse messageResponse = new MessageResponse();
            BeanUtils.copyProperties(msg, messageResponse);
            messageResponseList.add(messageResponse);
        }

        return messageResponseList;
    }

    public List<MessageResponse> getAllMessages() {
//        Pageable page = PageRequest.of(pageNum, pageSz);
        List<Message> messageList = messageRepository.findAll();
        return convertMsgListToMsgResponseList(messageList);
    }

    public List<MessageResponse> getAllMessagesByChatId(Long chatId, Integer pageNum, Integer pageSz) {
//        Pageable page = PageRequest.of(pageNum, pageSz);

        List<Message> messageList = messageRepository.findMessagesByChatId(chatId);
//        List<Message> messageList = messagePage.getContent();
        return convertMsgListToMsgResponseList(messageList);
    }

    public ResponseEntity<?> addNewMessage(MessageRequest messageRequest) {
        if (!chatRepository.existsById(messageRequest.getChatId())) {
            // throw new IllegalArgumentException("Chat with id " + messageRequest.getChatId() + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chat with id " + messageRequest.getChatId() + " does not exist");
        }

        Optional<Person> person = personRepository.findById(messageRequest.getSenderId());
        if (person.isEmpty()) {
           // throw new IllegalArgumentException("Person with id " + messageRequest.getSenderId() + " does not exist");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + messageRequest.getSenderId() + " does not exist");
        }
        // Check if the sender person exists
        if (!personRepository.existsById(messageRequest.getSenderId())) {
            //throw new IllegalArgumentException("Person with id " + messageRequest.getSenderId() + " does not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + messageRequest.getSenderId() + " does not exist");
        }
            Message newMessage = new Message();
            // newMessage.setGroupName(messageRequest.ge);
            newMessage.setChatId(messageRequest.getChatId());
            newMessage.setSenderId(messageRequest.getSenderId());
            newMessage.setSenderName(person.get().getName());
            newMessage.setMessageBody(messageRequest.getMessageBody());
            newMessage.setCreatedAt(LocalDateTime.now());
            newMessage.setUpdatedAt(LocalDateTime.now());

            List<Person> receiverPersonList = new ArrayList<>();

            for (Long id : messageRequest.getReceiversId()) {
                Person receiverPerson = personRepository.findById(id).get();
                receiverPersonList.add(receiverPerson);
            }

            newMessage.setReceivers(receiverPersonList);

            Message savedMessage = messageRepository.save(newMessage);

            MessageResponse messageResponse = new MessageResponse();
            BeanUtils.copyProperties(savedMessage, messageResponse);

            return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
        }
      //  return null;

    public ResponseEntity<?> updateMessage(Long msgId, MessageRequest messageRequest) {
        // Check if the message with msgId exists
        Message existingMessage = messageRepository.findById(msgId)
                .orElseThrow(() -> new IllegalArgumentException("Message with id " + msgId + " not found"));

        // Check if the chat with the given chatId exists
        if (!chatRepository.existsById(messageRequest.getChatId())) {
           // throw new IllegalArgumentException("Chat with id " + messageRequest.getChatId() + " does not exist");
      return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chat with id " + messageRequest.getChatId() + " does not exist");
        }

        // Check if the sender person with the given senderId exists
        if (!personRepository.existsById(messageRequest.getSenderId())) {
//throw new IllegalArgumentException("Person with id " + messageRequest.getSenderId() + " does not exist");
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person with id " + messageRequest.getSenderId() + " does not exist");

        }

        // Update message properties
        existingMessage.setChatId(messageRequest.getChatId());
        existingMessage.setSenderId(messageRequest.getSenderId());
        existingMessage.setMessageBody(messageRequest.getMessageBody());
        existingMessage.setUpdatedAt(LocalDateTime.now());

        // Update receivers
        List<Person> receiverPersonList = new ArrayList<>();
        for (Long id : messageRequest.getReceiversId()) {
            Person receiverPerson = personRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Person with id " + id + " not found"));
            receiverPersonList.add(receiverPerson);
        }
        existingMessage.setReceivers(receiverPersonList);

        // Save the updated message
        Message savedMessage = messageRepository.save(existingMessage);

        // Convert and return the updated message response
        MessageResponse messageResponse = new MessageResponse();
        BeanUtils.copyProperties(savedMessage, messageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(messageResponse);
    }

    public ResponseEntity<?> deleteMessage(Long msgId) {
        // Check if the message with msgId exists
        Message existingMessage = messageRepository.findBYid(msgId);
        if(existingMessage==null){
         return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not Found");
        }
        //System.out.println(existingMessage);

        // Delete the message
        messageRepository.delete(existingMessage);

        // Create a response indicating successful deletion
        MessageResponse deletedMessageResponse = new MessageResponse();
        BeanUtils.copyProperties(existingMessage, deletedMessageResponse);

        return ResponseEntity.status(HttpStatus.OK).body(deletedMessageResponse);
    }
}
