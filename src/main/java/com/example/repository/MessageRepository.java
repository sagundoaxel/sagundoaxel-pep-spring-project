package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

import java.util.Optional;



public interface MessageRepository extends JpaRepository<Message, Integer>{


    @Modifying
    @Transactional
    @Query("DELETE FROM Message WHERE messageId = :messageId")
    int deleteMessageById(Integer messageId);
    

}
