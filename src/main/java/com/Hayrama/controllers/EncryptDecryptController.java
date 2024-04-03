package com.Hayrama.controllers;

import com.Hayrama.models.EncrytDecrypt;
import com.Hayrama.services.EncryptDecryptService;
import com.Hayrama.utils.EnumMessages;
import com.Hayrama.utils.ReponseHttp;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value="/api")
public class EncryptDecryptController {

    @Autowired
    private EncryptDecryptService encryptDecryotSercice;
    
    @PostMapping("/encrypt")
    public ResponseEntity<ReponseHttp> encrypt(@RequestBody List<Map<String, Object>> arrayData) {
        try {
        	System.out.println("arrayData: " + arrayData);
        	ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),this.encryptDecryotSercice.encrytObject(arrayData));
        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }
    
    @PostMapping("/decrypt")
    public ResponseEntity<ReponseHttp> decrypt(@RequestBody Map<String, Object> data) {
        try {
        	Map<String, Object> object = this.encryptDecryotSercice.decryptTestStruct(data);
        	ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),object);
        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }

}
