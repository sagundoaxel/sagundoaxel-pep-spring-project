package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;




public interface MessageRepository extends JpaRepository<Message, Integer>{


    @Modifying
    @Transactional
    @Query("DELETE FROM Message WHERE messageId = :messageId")
    int deleteMessageById(Integer messageId);

    @Modifying
    @Transactional
    @Query("UPDATE Message SET messageText = :messageText WHERE messageId = :messageId")
    int updateMessageTextById(Integer messageId, String messageText);

    List<Message> findByPostedBy(Integer accountId);


    

}
