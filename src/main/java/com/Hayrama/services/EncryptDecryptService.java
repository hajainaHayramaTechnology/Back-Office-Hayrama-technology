package com.Hayrama.services;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.Hayrama.models.EncrytDecrypt;
import com.Hayrama.models.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class EncryptDecryptService {
	
	private static final String secretKey = "1234567890123456";
	
	public Map<String, Object> decryptTestStruct(Map<String, Object> objects) {
        try {
        	Map<String, Object> result = new HashMap<>();
        	for (String cle : objects.keySet()) {
        		result.put(decryptStandard(cle), decryptStandard((String) objects.get(cle)));
        		System.out.println("result: " + result);
        	}
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }
	
	public static String encryptStandard(String inputData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(inputData.getBytes());
            String result = Base64.encodeBase64String(encryptedBytes);
            System.out.println("result : " + result );
            result = permuter(result);
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }

	public static String decryptStandard(String inputData) {
        try {
        	inputData = resolvePermutation(inputData);
            byte[] decodedKey = secretKey.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(inputData));
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
            return null;
        }
    }
	
	public static String encryptFix(String inputData) {
        try {
            inputData = "fh1" + inputData + "nt";
            String result = encryptStandard(inputData);
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }
	
	public static String decryptFix(String inputData) {
        try {
            inputData = inputData.substring(0, 3);
            inputData = inputData.substring(inputData.length() - 2);
            String result = decryptStandard(inputData);
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }

	public static String permuter(String str) {
		if (str.length() < 9) {
	        System.out.println("La longueur de la chaîne est inférieure à 9 et ne peut pas être divisée en trois parties de longueur égale.");
	        return null;
	    }
    	String result = "";
//    	System.out.println("String : " + str );
        String partie1 = str.substring(0, str.length() / 3);
        String partie2 = str.substring(str.length() / 3, (2 * str.length()) / 3);
        String partie3 = str.substring((2 * str.length()) / 3);
        String troisDerniersPartie1 = partie1.substring(partie1.length() - 3);
        String troisDerniersPartie2 = partie2.substring(partie2.length() - 3);
        String troisDerniersPartie3 = partie3.substring(partie3.length() - 3);
        
        String sansTroisDerniers1 = partie1.replace(troisDerniersPartie1, "");
        String sansTroisDerniers2 = partie2.replace(troisDerniersPartie2, "");
        String sansTroisDerniers3 = partie3.replace(troisDerniersPartie3, "");
//        System.out.println("Parties : [" + partie1 + "] [" + partie2 + "] [" + partie3 + "]");
//        System.out.println("Trois Derniers Parties : [" + troisDerniersPartie1 + "] [" + troisDerniersPartie2 + "] [" + troisDerniersPartie3 + "]");
//        System.out.println("Sans Trois Derniers Parties : [" + sansTroisDerniers1 + "] [" + sansTroisDerniers2 + "] [" + sansTroisDerniers3 + "]");
        partie1 = troisDerniersPartie2 + sansTroisDerniers1;
        partie2 = troisDerniersPartie3 + sansTroisDerniers2;
        partie3 = troisDerniersPartie1 + sansTroisDerniers3;
        
        result = partie1 + partie2 + partie3;
//        System.out.println("Parties Apres : [" + partie1 + "] [" + partie2 + "] [" + partie3 + "]");

        return result;
    }
	
	public static String resolvePermutation(String str) {
		if (str.length() < 9) {
	        System.out.println("La longueur de la chaîne est inférieure à 9 et ne peut pas être divisée en trois parties de longueur égale.");
	        return null;
	    }
    	String result = "";
//    	System.out.println("resolvePermutation : " + str );
        String partie1 = str.substring(0, str.length() / 3);
        String partie2 = str.substring(str.length() / 3, (2 * str.length()) / 3);
        String partie3 = str.substring((2 * str.length()) / 3);
        
        String troisPremierPartie1 = partie1.substring(0,3);
        String troisPremierPartie2 = partie2.substring(0,3);
        String troisPremierPartie3 = partie3.substring(0,3);
        
        String sansTroisPremier1 = partie1.replace(troisPremierPartie1, "");
        String sansTroisPremier2 = partie2.replace(troisPremierPartie2, "");
        String sansTroisPremier3 = partie3.replace(troisPremierPartie3, "");
//        System.out.println("Parties : [" + partie1 + "] [" + partie2 + "] [" + partie3 + "]");
//        System.out.println("Trois Derniers Parties : [" + troisPremierPartie1 + "] [" + troisPremierPartie2 + "] [" + troisPremierPartie3 + "]");
//        System.out.println("Sans Trois Derniers Parties : [" + sansTroisPremier1 + "] [" + sansTroisPremier2 + "] [" + sansTroisPremier3 + "]");
        partie1 = sansTroisPremier1 + troisPremierPartie3;
        partie2 = sansTroisPremier2 + troisPremierPartie1;
        partie3 = sansTroisPremier3 + troisPremierPartie2;
        
        result = partie1 + partie2 + partie3;
//        System.out.println("Parties Apres : [" + partie1 + "] [" + partie2 + "] [" + partie3 + "]");

        return result;
    }
	
	public static List<Map<String, Object>> encrytObject(List<Map<String, Object>> objetJson) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> map : objetJson) {
			Map<String, Object> testMap = new HashMap<>();
			for (String cle : map.keySet()) {
				testMap.put(encryptStandard(cle), encryptStandard(String.valueOf(map.get(cle))));
			}
			resultList.add(testMap);
			System.out.println("objetJson: " + objetJson);			
		}
        return resultList;
    }
	
	public static List<Map<String, Object>> decrytObject(List<Map<String, Object>> objetJson) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> map : objetJson) {
			Map<String, Object> testMap = new HashMap<>();
			for (String cle : map.keySet()) {
//				EncrytDecrypt encrytDecrypt = new EncrytDecrypt();
//				testMap.put(cle, decryptStandard(encrytDecrypt));
			}
			resultList.add(testMap);
			System.out.println("objetJson: " + objetJson);			
		}
        return resultList;
    }
}
