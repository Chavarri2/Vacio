// Escena.java
/**
 * Clase que representa una escena en el juego.
 * Hereda de EntidadJuego.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pizco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

import java.util.Vector;
import java.util.List;

public class Escena extends EntidadJuego {
    /** Descripción. */
    private String descripcion;
    
    /** Lista de menús (Vector). */
    private List<Menu> menus = new Vector<>();

    /**
     * Constructor por defecto.
     */
    public Escena() {
        super();
        this.menus = new Vector<>();
    }

    /**
     * Constructor con nombre y descripción.
     * @param nombre Nombre.
     * @param descripcion Descripción.
     */
    public Escena(String nombre, String descripcion) {
        super(nombre);
        this.descripcion = descripcion;
        this.menus = new Vector<>();
    }

    /**
     * Constructor con nombre.
     * @param nombre Nombre.
     */
    public Escena(String nombre) {
        super(nombre);
        this.menus = new Vector<>();
    }

    // Getters y Setters
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}