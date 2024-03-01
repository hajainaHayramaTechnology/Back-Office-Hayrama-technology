/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hayrama.utils;

/**
 *
 * @author hp
 */
public class ReponseHttp {

    private String message;
    private Object contenu;

    public ReponseHttp() {
    }

    public ReponseHttp(String message, Object contenu) {
        this.message = message;
        this.contenu = contenu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContenu() {
        return contenu;
    }

    public void setContenu(Object contenu) {
        this.contenu = contenu;
    }
}
