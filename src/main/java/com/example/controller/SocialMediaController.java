package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import com.example.exception.UsernameExistsException;
import com.example.exception.UsernameAndPasswordMismatchException;
import com.example.exception.MessageNotCreatedException;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private MessageService messageService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Account account){
        try {
            Account dbAccount = accountService.saveAccount(account);
            return ResponseEntity.status(HttpStatus.OK).body(dbAccount);
        } catch (UsernameExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Account account){
        try {
            Account dbAccount = accountService.processUserLogin(account);
            return ResponseEntity.status(HttpStatus.OK).body(dbAccount);
        } catch (UsernameAndPasswordMismatchException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/messages")
    public ResponseEntity createMessage(@RequestBody Message message){
        try {
            Message dbAccount = messageService.saveMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(dbAccount);
        } catch (MessageNotCreatedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/messages")
    public ResponseEntity getAllMessages(){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAllMessages());
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable Integer messageId){
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getMessageById(messageId).orElse(null));
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable Integer messageId){
        Integer numRowsAffected = messageService.deleteMessageById(messageId);
        return ResponseEntity.status(HttpStatus.OK).body((numRowsAffected == 1) ? numRowsAffected : null);
    }



}
