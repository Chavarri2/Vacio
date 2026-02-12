// Menu.java
/**
 * Clase que representa un menú de opciones.
 * Implementa Interactuar.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

import visual.Interactuar;

public class Menu implements Interactuar {
    /** Texto del menú. */
    private String texto;

    /** Clave siguiente. */
    private String claveSiguiente;

    /**
     * Constructor por defecto.
     */
    public Menu() {
    }

    /**
     * Constructor con texto y clave.
     * 
     * @param texto          Texto.
     * @param claveSiguiente Clave.
     */
    public Menu(String texto, String claveSiguiente) {
        this.texto = texto;
        this.claveSiguiente = claveSiguiente;
    }

    // Getters y Setters
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getClaveSiguiente() {
        return claveSiguiente;
    }

    public void setClaveSiguiente(String claveSiguiente) {
        this.claveSiguiente = claveSiguiente;
    }

    @Override
    public void mostrar() {
        System.out.println(texto);
    }
}