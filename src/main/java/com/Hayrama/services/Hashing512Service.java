package com.Hayrama.services;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class Hashing512Service {
	
	 public static String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");

            byte[] hash = digest.digest(input.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            System.out.println("hashString: " + hexString.toString());
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
	 
	private static boolean verifiyHash(String hash, String data) {
		try {
            boolean result = false;
            String hashData = hashString(data);
            if(hashData == hash) {
            	result = true;
            }
            return result;
        } catch (Exception E) {
        	throw E;
        }
	}
}
