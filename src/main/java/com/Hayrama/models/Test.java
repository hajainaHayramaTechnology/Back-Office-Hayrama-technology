package com.Hayrama.models;
 
import java.sql.Date;
import javax.persistence.Column;

import javax.persistence.*;


@Entity
@Table(name = "Test")
public class Test{
        
    @Id
    @Column(name = "idTest", unique = true , nullable = false )
	private int idTest;
    
    @Column(name = "nom", nullable = false )
	private String nom;
    
    @Column(name = "date", nullable = false )
	private Date date;
	
	

	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Test(int id, String nom, Date date) {
		super();
		this.idTest = id;
		this.nom = nom;
		this.date = date;
	}

	public int getId() {
		return idTest;
	}

	public void setId(int id) {
		this.idTest = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    public int getIdTest() {
        return idTest;
    }

    public void setIdTest(int idTest) {
        this.idTest = idTest;
    }
}
