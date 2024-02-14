package com.example.demo.serviceImpl;

import com.example.demo.dto.request.ChatRequest;
import com.example.demo.dto.response.ChatResponse;
import com.example.demo.model.Chat;
import com.example.demo.model.Person;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.PersonRepository;
import com.example.demo.services.ChatService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final PersonRepository personRepository;

    @Autowired
    public ChatServiceImpl(ChatRepository chatRepository, PersonRepository personRepository) {
        this.chatRepository = chatRepository;
        this.personRepository = personRepository;
    }

    private List<ChatResponse> convertChatListToChatResponseList(List<Chat> chatList) {
        List<ChatResponse> chatResponseList = new ArrayList<>();
        for (Chat chat : chatList) {
            ChatResponse chatResponse = new ChatResponse();
            BeanUtils.copyProperties(chat, chatResponse);
            chatResponseList.add(chatResponse);
        }

        return chatResponseList;
    }

    public List<ChatResponse> getAllChats(Integer pageNum, Integer pageSz) {
        Pageable page = PageRequest.of(pageNum, pageSz);
        Page<Chat> chatsPage = chatRepository.findAll(page);
        List<Chat> chatList = chatsPage.getContent();
        return convertChatListToChatResponseList(chatList);
    }

    public ChatResponse getChatByChatId(Long id) {
        Chat chat = chatRepository.findById(id).get();
        // System.out.println(chat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(chat, chatResponse);
        //Set<Person>personSet=chatResponse.getPersonSet();
        //personSet.removeIf(p -> id.equals(p.getId()));
        return chatResponse;
    }

    public ResponseEntity<?> addNewChat(ChatRequest chatRequest) {
        Set<Person> personSet = new HashSet<>();
        for (Long id : chatRequest.getPersonIds()) {
            Optional<Person> personOptional = personRepository.findById(id);
            if (personOptional.isPresent()) {
                personSet.add(personOptional.get());
            } else {
                throw new IllegalStateException("Person with id " + id + " doesn't exist!!");
            }
        }
        Chat newChat = new Chat();
//        List<String> listofnames = new ArrayList<>();
//
//        if(personSet.size()==2){
//            for(Person p : personSet){
//                listofnames.add(p.getName());
//            }
//            newChat.setListOfName(listofnames);
//       }
//        else if(personSet.size()>2){
//            newChat.setListOfName(listofnames);
//        }
        List<Chat> chatName = chatRepository.findChatName(chatRequest.getName());
        if (!chatName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Chat already exists");
        }
        if (chatRequest.getName() != null) {
            newChat.setName(chatRequest.getName());
        }
        newChat.setIsGroup(chatRequest.getGroup());
        newChat.setPersonSet(personSet);
        newChat.setCreatedAt(LocalDateTime.now());
        newChat.setUpdatedAt(LocalDateTime.now());

        Chat savedChat = chatRepository.save(newChat);
//        System.out.println(savedChat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(savedChat, chatResponse);
//        System.out.println(chatResponse);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponse);
    }

    public ResponseEntity<?> getChatsByUsersId(Long personId) {
        List<Chat> chatList = chatRepository.findChatsByPersonId(personId);
        //  System.out.println(chatList);
        if (chatList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No user with id " + personId + " present in any chat");
        }

        for (Chat c : chatList) {
            Set<Person> personSet = c.getPersonSet();
            if (personSet.size() == 2) {
                personSet.removeIf(p -> Objects.equals(p.getId(), personId));

            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(convertChatListToChatResponseList(chatList));
    }

    public ResponseEntity<?> addUserInChat(ChatRequest chatRequest, Long chatId) {
        // chat request m aaega List<PersonIds>
        Chat chat = chatRepository.findById(chatId).get();
        Set<Long> personIds = chatRequest.getPersonIds();

        Set<Person> personSet = new HashSet<>(chat.getPersonSet());
        for (Long personId : personIds) {
            Person person = personRepository.findById(personId).get();
            for (Person p : personSet) {
                if (p == person) {
                    return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Person already present in the chat");
                }
            }
            personSet.add(person);
        }
        chat.setPersonSet(personSet);
        Chat savedChat = chatRepository.save(chat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(savedChat, chatResponse);
        return ResponseEntity.status(HttpStatus.OK).body(chatResponse);
    }

    public ResponseEntity<?> removeUserFromChat(ChatRequest chatRequest, Long chatId) {
        // chat request m aaega List<PersonIds>
        Chat chat = chatRepository.findById(chatId).get();
        Set<Long> personIds = chatRequest.getPersonIds();
        Set<Person> personSet = new HashSet<>(chat.getPersonSet());
        System.out.println(personSet);
        System.out.println(personIds);
        for (Long personId : personIds) {
            // Person person = personRepository.findBYid(personId);
            boolean b = false;
            Person removedPerson = new Person();
            for (Person p : personSet) {
                if (Objects.equals(p.getId(), personId)) {
                    // personSet.remove(p);
                    removedPerson = p;
                    b = true;
                    break;
                }
            }
            if (b) {
                personSet.remove(removedPerson);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person doesn't exists");
            }

            // if (person == null) {
            // }
            //  else{
            //personSet.remove(person);
            //}}
        }
        chat.setPersonSet(personSet);
        Chat savedChat = chatRepository.save(chat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(savedChat, chatResponse);
        return ResponseEntity.ok().body(chatResponse);
    }

}
