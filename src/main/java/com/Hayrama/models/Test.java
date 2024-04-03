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
@Entity
@Table(name = "Tests")
public class Test{
        
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("idTest")
	private Long idTest;
	
	@JsonProperty("nom")
	private String nom;
	
	@JsonProperty("date")
	private Date date;
	
	
	
	public Map<String, Object> convertsToMap(List<Test> tests, String... attributeNames) {
        Map<String, Object> result = new HashMap<>();
        for (Test test : tests) {
            StringBuilder keyBuilder = new StringBuilder();
            for (String attributeName : attributeNames) {
                try {
                    Field field = Test.class.getDeclaredField(attributeName);
                    Object value = field.get(test);
                    keyBuilder.append(value.toString()).append("-");
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            String key = keyBuilder.toString();
            if (!key.isEmpty()) {
                result.put(key, test);
            }
        }
        return result;
    }
}
