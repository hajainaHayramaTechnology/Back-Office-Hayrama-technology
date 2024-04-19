package com.Hayrama.controllers;

import java.sql.Date;
import java.util.Objects;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.Hayrama.models.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("userName", chatMessage.getNickName());
		return chatMessage;
	}
	
	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		return chatMessage;
	}
}
