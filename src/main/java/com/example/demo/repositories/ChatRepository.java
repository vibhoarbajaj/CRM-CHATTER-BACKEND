package com.example.demo.repositories;

import com.example.demo.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("SELECT c FROM Chat c JOIN c.personSet p WHERE p.id = :givenId")
    List<Chat> findChatsByPersonId(Long givenId);

    @Query("select c from Chat c where c.name = ?1")
    List<Chat> findChatName(String name);
}
