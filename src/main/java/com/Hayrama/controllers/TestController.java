package com.Hayrama.controllers;

import com.Hayrama.models.Test;
import com.Hayrama.services.EncryptDecryptService;
import com.Hayrama.services.Hashing512Service;
import com.Hayrama.services.TestService;
import com.Hayrama.services.UtilityServices;
import com.Hayrama.utils.EnumMessages;
import com.Hayrama.utils.ReponseHttp;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/test")
public class TestController{

    @Autowired
    private TestService testService;
    
    @Autowired
    private Hashing512Service hashing512Service;
    
    @Autowired
    private EncryptDecryptService encryptDecryptService;
    
    private UtilityServices utilityServices;
    
    @GetMapping("/select")
    public ResponseEntity<ReponseHttp> selectTest() {
        try{
	    	List<Test> tests = this.testService.getAll();
			List<Map<String, Object>> arrayTest = this.testService.convertTestsToMapArray(tests);
			System.out.println("arrayTest: " + arrayTest);
			ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),this.encryptDecryptService.encrytObject(arrayTest));
	        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }
    
    @PostMapping("/insert")
    public ResponseEntity<ReponseHttp> insert(@RequestBody Map<String, Object> test,@RequestHeader("Hash") String heading) {
        try{
        	boolean verifyHash = this.hashing512Service.verifiyHash(heading,test.toString());
        	if(verifyHash) {
        		Map<String, Object> object = this.encryptDecryptService.decryptTestStruct(test);
        		Test testObject = new Test();
        		testObject = this.testService.convertHashMaoToTests(object);
//        	this.testService.save(testObject);
        		ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),testObject);
        		return new ResponseEntity<>(rep, HttpStatus.OK);
        	}else {
        		throw new RuntimeException("Hash verification failed please try again");
        	}
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }
    
    @PostMapping("/update")
    public ResponseEntity<ReponseHttp> update(@RequestBody Test test ) {
        try{
        ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),this.testService.save(test));
        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }
    
    @GetMapping("/selectSansEncrypt")
    public ResponseEntity<ReponseHttp> selectSansEncrypt() {
        try{
			ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),this.testService.getAll());
	        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }

}
