package com.Hayrama.services;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


@Service
public class EncryptDecryptService {
	
	private static final String secretKey = "1234567890123456";
	
	public static String encryptStandard(String inputData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(inputData.getBytes());
            return Base64.encodeBase64String(encryptedBytes);
        } catch (Exception e) {
            System.err.println("Encryption error: " + e.getMessage());
            return null;
        }
    }

	public static String decryptStandard(String inputData) {
        try {
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

	public static String permuterTroisDerniers(String str) {
        if (str.length() % 3 == 0) {
            String partie1 = str.substring(0, str.length() / 3);
            String partie2 = str.substring(str.length() / 3, (2 * str.length()) / 3);
            String partie3 = str.substring((2 * str.length()) / 3);
            String troisDerniersPartie2 = partie2.substring(partie2.length() - 3);
            String troisDerniersPartie3 = partie3.substring(partie3.length() - 3);

            partie2 = partie2.substring(0, partie2.length() - 3) + troisDerniersPartie3;
            partie3 = partie3.substring(0, partie3.length() - 3) + troisDerniersPartie2;

            System.out.println("Avant permutation : [" + partie1 + "] [" + partie2 + "] [" + partie3 + "]");
            System.out.println("Après permutation : [" + partie1 + "] [" + partie2 + "] [" + partie3 + "]");

            return partie1 + partie2 + partie3;
        } else {
            System.out.println("La longueur de la chaîne n'est pas divisible par 3.");
            return null;
        }
    }
}
