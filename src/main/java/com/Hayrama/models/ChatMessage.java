package com.Hayrama.models;
 
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ChatMessage")
public class ChatMessage{
        
	@JsonProperty("nickName")
	private String nickName;
	
	@JsonProperty("content")
	private String content;
	
	@JsonProperty("date")
	private Date timestamp;
}
