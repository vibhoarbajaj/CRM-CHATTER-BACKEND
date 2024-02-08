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
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(chat, chatResponse);
        return chatResponse;
    }

    public ChatResponse addNewChat(ChatRequest chatRequest) {
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
        if (chatRequest.getName() != null) {
            newChat.setName(chatRequest.getName());
        }
        newChat.setGroup(chatRequest.getGroup());
        newChat.setPersonSet(personSet);
        newChat.setCreatedAt(LocalDateTime.now());
        newChat.setUpdatedAt(LocalDateTime.now());

        Chat savedChat = chatRepository.save(newChat);
//        System.out.println(savedChat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(savedChat, chatResponse);
//        System.out.println(chatResponse);
        return chatResponse;
    }

    public List<ChatResponse> getChatsByUsersId(Long personId) {
        List<Chat> chatList = chatRepository.findChatsByPersonId(personId);
        System.out.println(chatList);
        return convertChatListToChatResponseList(chatList);
    }

    public ChatResponse addUserInChat(ChatRequest chatRequest, Long chatId) {
        // chat request m aaega List<PersonIds>
        Chat chat = chatRepository.findById(chatId).get();
        Set<Long> personIds = chatRequest.getPersonIds();
        Set<Person> personSet = new HashSet<>(chat.getPersonSet());
        for (Long personId : personIds) {
            Person person = personRepository.findById(personId).get();
            personSet.add(person);
        }
        chat.setPersonSet(personSet);
        Chat savedChat = chatRepository.save(chat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(savedChat, chatResponse);
        return chatResponse;
    }

    public ChatResponse removeUserFromChat(ChatRequest chatRequest, Long chatId) {
        // chat request m aaega List<PersonIds>
        Chat chat = chatRepository.findById(chatId).get();
        Set<Long> personIds = chatRequest.getPersonIds();
        Set<Person> personSet = new HashSet<>(chat.getPersonSet());
        for (Long personId : personIds) {
            Person person = personRepository.findById(personId).get();
            personSet.remove(person);
        }
        chat.setPersonSet(personSet);
        Chat savedChat = chatRepository.save(chat);
        ChatResponse chatResponse = new ChatResponse();
        BeanUtils.copyProperties(savedChat, chatResponse);
        return chatResponse;
    }

}
