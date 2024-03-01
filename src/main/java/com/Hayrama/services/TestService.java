package com.Hayrama.services;

import com.Hayrama.models.Test;
import com.Hayrama.repository.TestRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestService {
	
	@Autowired
	private TestRepository testRepo;


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
