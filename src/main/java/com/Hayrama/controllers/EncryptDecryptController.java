package com.Hayrama.controllers;

import com.Hayrama.services.EncryptDecryptService;
import com.Hayrama.utils.EnumMessages;
import com.Hayrama.utils.ReponseHttp;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value="/api")
public class EncryptDecryptController {

    @Autowired
    private EncryptDecryptService encryptDecryotSercice;
    
    @GetMapping("/encrypt")
    public ResponseEntity<ReponseHttp> encrypt(@RequestParam(name = "data") String data) {
        try {
        	String response = this.encryptDecryotSercice.encryptStandard(data);
        	ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),response);
        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }
    
    @GetMapping("/decrypt")
    public ResponseEntity<ReponseHttp> decrypt(@RequestParam(name = "data") String data) {
        try {
        	String response = this.encryptDecryotSercice.decryptStandard(data);
        	ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),response);
        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }

}
