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
        	Map<String, Object> partie = new HashMap<>();
        	for (String cle : objects.keySet()) {
        		System.out.println("cle: " + cle);
        		partie = (Map<String, Object>) objects.get(cle);
        		System.out.println("partie: " + partie);
        		EncrytDecrypt encrytDecrypt = new EncrytDecrypt();
        		encrytDecrypt.setPartie1(partie.get("partie1").toString());
        		encrytDecrypt.setPartie2(partie.get("partie2").toString());
        		encrytDecrypt.setPartie3(partie.get("partie3").toString());
        		result.put(cle, decryptStandard(encrytDecrypt));
        		System.out.println("result: " + result);
        	}
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }
	
	public static EncrytDecrypt encryptStandard(String inputData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(inputData.getBytes());
            String result = Base64.encodeBase64String(encryptedBytes);
            EncrytDecrypt objet = new EncrytDecrypt();
            objet = permuter(result);
            return objet;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }

	public static String decryptStandard(EncrytDecrypt inputData) {
        try {
        	String resolvePermutation = resolvePermutation(inputData);
            byte[] decodedKey = secretKey.getBytes();
            SecretKeySpec secretKeySpec = new SecretKeySpec(decodedKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(Base64.decodeBase64(resolvePermutation));
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            System.err.println("Decryption error: " + e.getMessage());
            return null;
        }
    }
	
	public static EncrytDecrypt encryptFix(String inputData) {
        try {
            inputData = "fh1" + inputData + "nt";
            EncrytDecrypt result = new EncrytDecrypt();
            result = encryptStandard(inputData);
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }
	
	public static String decryptFix(EncrytDecrypt data) {
        try {
        	String result = decryptStandard(data);
        	result = result.substring(0, 3);
        	result = result.substring(result.length() - 2);
            return result;
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }

	public static EncrytDecrypt permuter(String str) {
		if (str.length() < 9) {
	        System.out.println("La longueur de la chaîne est inférieure à 9 et ne peut pas être divisée en trois parties de longueur égale.");
	        return null;
	    }
    	String result = "";
    	
        String partie1 = str.substring(0, str.length() / 3);
        String partie2 = str.substring(str.length() / 3, (2 * str.length()) / 3);
        String partie3 = str.substring((2 * str.length()) / 3);
        String troisDerniersPartie1 = partie1.substring(partie1.length() - 3);
        String troisDerniersPartie2 = partie2.substring(partie2.length() - 3);
        String troisDerniersPartie3 = partie3.substring(partie3.length() - 3);
        
        String sansTroisDerniers1 = partie1.replace(troisDerniersPartie1, "");
        String sansTroisDerniers2 = partie2.replace(troisDerniersPartie2, "");
        String sansTroisDerniers3 = partie3.replace(troisDerniersPartie3, "");
        
        partie1 = troisDerniersPartie2 + sansTroisDerniers1;
        partie2 = troisDerniersPartie3 + sansTroisDerniers2;
        partie3 = troisDerniersPartie1 + sansTroisDerniers3;
        
        EncrytDecrypt objet = new EncrytDecrypt();
        objet.setPartie1(partie1);
        objet.setPartie2(partie2);
        objet.setPartie3(partie3);
        
        result = partie1 + partie2 + partie3;
        
        return objet;
    }
	
	public static String resolvePermutation(EncrytDecrypt objet) {
    	String result = "";
    	
    	String partie1 = objet.getPartie1();
        String partie2 = objet.getPartie2();
        String partie3 = objet.getPartie3();
        
        String troisPremierPartie1 = partie1.substring(0,3);
        String troisPremierPartie2 = partie2.substring(0,3);
        String troisPremierPartie3 = partie3.substring(0,3);
        
        String sansTroisPremier1 = partie1.replace(troisPremierPartie1, "");
        String sansTroisPremier2 = partie2.replace(troisPremierPartie2, "");
        String sansTroisPremier3 = partie3.replace(troisPremierPartie3, "");
        
        partie1 = sansTroisPremier1 + troisPremierPartie3;
        partie2 = sansTroisPremier2 + troisPremierPartie1;
        partie3 = sansTroisPremier3 + troisPremierPartie2;
        
        result = partie1 + partie2 + partie3;
        
        return result;
    }
	
	public static List<Map<String, Object>> encrytObject(List<Map<String, Object>> objetJson) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (Map<String, Object> map : objetJson) {
			Map<String, Object> testMap = new HashMap<>();
			for (String cle : map.keySet()) {
				testMap.put(cle, encryptStandard(String.valueOf(map.get(cle))));
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
				EncrytDecrypt encrytDecrypt = new EncrytDecrypt();
				testMap.put(cle, decryptStandard(encrytDecrypt));
			}
			resultList.add(testMap);
			System.out.println("objetJson: " + objetJson);			
		}
        return resultList;
    }
}
