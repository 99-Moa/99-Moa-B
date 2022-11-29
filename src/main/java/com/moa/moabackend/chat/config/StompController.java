package com.moa.moabackend.chat.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class StompController {

// @Autowired
// private SimpMessageSendingOperations messagingTemplate;

    @MessageMapping("/message") // /app/message 로 받으면
    @SendTo("/topic/message") // return 값을 /topic/message 로 넘겨준다.
    public String message(String message) {
        System.out.println("StompController 메세지 : " + message);
        return message;
    }
}