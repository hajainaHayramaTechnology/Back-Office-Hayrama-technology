package com.Hayrama.services;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilityServices {
	
	 // Surcharge de la méthode pour accepter une seule instance d'objet
    public static <T> Map<String, T> convertsToMap(T object, String... attributeNames) {
        Map<String, T> result = new HashMap<>();
        StringBuilder keyBuilder = new StringBuilder();
        for (String attributeName : attributeNames) {
            try {
                // Obtenez la valeur de chaque attribut spécifié par son nom
                Field field = object.getClass().getDeclaredField(attributeName);
                field.setAccessible(true); // Autorise l'accès aux attributs privés
                Object value = field.get(object);
                keyBuilder.append(value.toString()).append("-");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Gérer l'erreur si l'attribut n'existe pas ou s'il y a un problème d'accès
                e.printStackTrace();
            }
        }
        String key = keyBuilder.toString();
        if (!key.isEmpty()) {
            result.put(key, object);
        }
        return result;
    }

    public static Map<String, Object> convertsToMap(List<?> objects, String... attributeNames) {
        Map<String, Object> result = new HashMap<>();
        for (Object object : objects) {
            StringBuilder keyBuilder = new StringBuilder();
            for (String attributeName : attributeNames) {
                try {
                    // Obtenez la valeur de chaque attribut spécifié par son nom
                    Field field = object.getClass().getDeclaredField(attributeName);
                    field.setAccessible(true); // Autorise l'accès aux attributs privés
                    Object value = field.get(object);
                    keyBuilder.append(value.toString()).append("-");
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    // Gérer l'erreur si l'attribut n'existe pas ou s'il y a un problème d'accès
                    e.printStackTrace();
                }
            }
            String key = keyBuilder.toString();
            if (!key.isEmpty()) {
                result.put(key, object);
            }
        }
        return result;
    }

}
