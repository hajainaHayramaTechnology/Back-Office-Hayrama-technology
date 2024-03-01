package com.Hayrama.services;

import com.Hayrama.models.Test;
import com.Hayrama.repository.TestRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepo;
	
	private static final String secretKey = "secretKey";
	
	public static String encrypt(String inputData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] encryptedBytes = cipher.doFinal(inputData.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

	public static String decrypt(String inputData) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] decodedBytes = Base64.getDecoder().decode(inputData);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


	public static String permuterTroisDerniers(String str) {
        if (str.length() % 3 == 0) {
            // Diviser la chaîne en trois parties égales
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



	public Test save(Test entity) {
		// TODO Auto-generated method stub
		return testRepo.save(entity);
	}


	public Test getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public List<Test> getAll() {
		// TODO Auto-generated method stub
		return testRepo.findAll();
	}


	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	

	public List<Test> gettout() throws Exception{
		List<Test> listesalaire=new ArrayList<Test>();
		try {
			listesalaire=testRepo.getAll();
		}catch(Exception E) {
			throw E;
		}
		return listesalaire;
	}

}
