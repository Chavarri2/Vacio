// Utilidades.java
/**
 * Clase auxiliar para utilidades generales.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package com.vacio.utils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;

public class Utilidades {
    // Métodos auxiliares si es necesario
    public static JsonNode leerArchivo () {
        LeerFile leer = new LeerFile();
        return leer.GetJsonNode("src/main/resources/Strings/Script.json");
    }
    public static List < String> jsonToList(JsonNode json){
        List <String> resultado = new ArrayList<>();
        if (json.isArray()) {
            for (JsonNode n : json) {
                resultado.add(n.asText());
            }
        }
        return resultado;

    }
    public static void typeWriter(String texto, long delayMs) {
        for (char c : texto.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }
}