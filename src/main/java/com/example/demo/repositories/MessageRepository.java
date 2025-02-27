package com.example.demo.repositories;

import com.example.demo.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m where m.chatId=?1")
    List<Message> findMessagesByChatId(Long chatId);
    @Query("select m from Message m where m.id=?1")
    Message findBYid(Long id);

}
