
/**
 Clase que representa un menú de opciones.
 Implementa Interactuar.
 
 @author Carlos Abraham Chavarri Valera
 @author Violeta Pisco
 @version 1.0
 @since 2026-02-11
 **/
package modelo;

import visual.Interactuar;

public class Respuestas implements Interactuar {
    private String texto;
    // Id siguiente escena/menu
    private String clave;

    public Respuestas() {
    }

    /**
     * Constructor con texto y clave.
     * 
     * @param texto Texto.
     * @param clave Clave.
     **/
    public Respuestas(String texto, String clave) {
        this.texto = texto;

    }

    // Getters y Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String claveSiguiente) {
        this.clave = claveSiguiente;
    }

    @Override
    public void mostrar() {
        System.out.println(texto);
    }
}