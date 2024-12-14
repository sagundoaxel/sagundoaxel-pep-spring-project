package com.example.service;

import org.springframework.stereotype.Service;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
// import com.example.service.AccountService;

import com.example.exception.MessageNotCreatedException;


@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountService accountService;

    @Autowired 
    public MessageService(MessageRepository messageRepository, AccountService accountService){
        this.messageRepository = messageRepository;
        this.accountService = accountService;
    }

    public boolean existsById(Integer id){
        return messageRepository.existsById(id);
    }

    public Message saveMessage(Message message){
        if(message.getMessageText() == null || message.getMessageText().isBlank()){
            throw new MessageNotCreatedException("Message is blank");
        }

        if (message.getMessageText().length() > 255){
            throw new MessageNotCreatedException("Message is too long");
        }

        if (!(accountService.existsById(message.getPostedBy()))){
            throw new MessageNotCreatedException("PostedBy UserId " + message.getPostedBy() + " does not exist");
        }

        return messageRepository.save(message);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id){
        return messageRepository.findById(id);
    }

    public int deleteMessageById(Integer id){
        return messageRepository.deleteMessageById(id);
    }
        


}
