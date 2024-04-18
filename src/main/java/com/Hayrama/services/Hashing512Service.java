package com.Hayrama.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;


@Service
public class Hashing512Service {
	
	 public String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            byte[] hash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	 
	public boolean verifiyHash(String hashHeading, String body) {
		try {
			System.out.println("body: " + body);
            boolean result = false;
            String hashData = hashString(body);
            System.out.println("hashHeading: " + hashHeading);
            System.out.println("hashData: " + hashData);
            if(hashHeading.equals(hashData)) {
            	result = true;
            }
            return result;
        } catch (Exception E) {
        	throw E;
        }
	}
	
	public String convertMapToString(Map<String, Object> data) throws Exception {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(data);
		} catch (Exception E) {
        	throw E;
        }
	}
}
