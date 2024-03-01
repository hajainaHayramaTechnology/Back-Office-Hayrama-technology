/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Hayrama.utils;

/**
 *
 * @author Tantely
 */
public enum EnumMessages {
    SELECT_SUCCESS("Données recupérées"),
    INSERTION_AVEC_SUCCESS("Donnée enregistrée"),
    DELETE_SUCCESS("Donnée supprimeée"),
    UPDATE_AVEC_SUCCESS("Donnée modifiée");
    
    private final String message;

    private EnumMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
