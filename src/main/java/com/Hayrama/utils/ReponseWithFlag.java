/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hayrama.utils;

/**
 *
 * @author hp
 */
public class ReponseWithFlag {

    private String message;
    private Object contenu;
    private Boolean flag;

    public ReponseWithFlag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReponseWithFlag(String message, Object contenu, Boolean flag) {
		super();
		this.message = message;
		this.contenu = contenu;
		this.flag = flag;
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

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
    
}
