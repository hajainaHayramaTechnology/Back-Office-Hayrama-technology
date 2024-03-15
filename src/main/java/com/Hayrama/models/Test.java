package com.Hayrama.models;
 
import java.sql.Date;

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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Tests")
public class Test{
        
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("idTest")
	private Long idTest;
	private String nom;
	private Date date;
}
