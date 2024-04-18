package com.Hayrama.services;

import com.Hayrama.models.EncrytDecrypt;
import com.Hayrama.models.Test;
import com.Hayrama.repository.TestRepository;
import java.util.List;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepo;
	
	@Autowired
	private EncryptDecryptService encryptDecryptService;

	public Test save(Test entity) {
		return testRepo.save(entity);
	}


	public Test getById(Integer id) {
		return null;
	}


	public List<Test> getAll() {
		return testRepo.findAll();
	}


	public void delete(Integer id) {
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
	
	public List<Map<String, Object>> convertTestsToMapArray(List<Test> tests) {
		List<Map<String, Object>> resultList = new ArrayList<>();
        for (Test test : tests) {
            Map<String, Object> testMap = new HashMap<>();
            testMap.put("id", test.getIdTest());
            testMap.put("nom", test.getNom());
            testMap.put("date", test.getDate());
            System.out.println("testMap: " + testMap);
            resultList.add(testMap);
            System.out.println("convertTestsToMapArray: " + resultList);
        }
        return resultList;
    }
	
	public Test convertHashMaoToTests(Map<String, Object> object) {
		Test test = new Test();
		Object idObject = object.get("id");
		String dateString = (String) object.get("date");
		LocalDate localDate = LocalDate.parse(dateString);
		ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneOffset.UTC);
		Instant instant = zonedDateTime.toInstant();
		Date utilDate = Date.from(instant);
		test.setDate(new java.sql.Date(utilDate.getTime()));
		System.out.println("dateObject: " + utilDate);
		if (object.get("id") instanceof Long) {
	        test.setIdTest((Long) idObject);
	    }
        test.setNom((String) object.get("nom"));
        return test;
    }
}
