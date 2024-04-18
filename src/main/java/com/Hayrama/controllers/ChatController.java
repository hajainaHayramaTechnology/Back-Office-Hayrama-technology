package com.Hayrama.controllers;

import java.sql.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.Hayrama.models.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat")
	@SendTo("/topic/message")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
//		chatMessage.setTimestamp(new Date());
		return chatMessage;
	}
}
