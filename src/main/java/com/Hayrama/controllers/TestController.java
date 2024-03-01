package com.Hayrama.controllers;

import com.Hayrama.services.TestService;
import com.Hayrama.utils.EnumMessages;
import com.Hayrama.utils.ReponseHttp;
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@CrossOrigin(origins="*")
@RestController
@RequestMapping(value="/equipe")
public class TestController {
    
    @Autowired
    private JdbcTemplate jdbc;

    @Autowired
    private TestService testSercice;


    @GetMapping("/test")
    public String test() {
        return "Mamy";
    }
    
    @GetMapping("/select")
    public ResponseEntity<ReponseHttp> selectEquipe() {
        try (Connection con=jdbc.getDataSource().getConnection()) {
            con.setAutoCommit(false);
        ReponseHttp rep = new ReponseHttp(EnumMessages.SELECT_SUCCESS.getMessage(),this.testSercice.getAll());
        return new ResponseEntity<>(rep, HttpStatus.OK);
        } catch (Exception e) {
                e.printStackTrace();
                ReponseHttp rep = new ReponseHttp(e.getMessage(),null);
                return new ResponseEntity<ReponseHttp>(rep, HttpStatus.BAD_REQUEST);
        }finally {
        }
    }

}
