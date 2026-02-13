/**
 * Clase que representa una escena en el juego.
 * Hereda de EntidadJuego.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

import java.util.Vector;
import java.util.List;

public class Escena extends EntidadJuego {

    private String descripcion;
    private List<Respuestas> menus = new Vector<>();

    //Constructores

    public Escena() {
        super();
        this.menus = new Vector<>();
    }

    public Escena(String nombre, String descripcion) {
        super(nombre);
        this.descripcion = descripcion;
        this.menus = new Vector<>();
    }

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

    public List<Respuestas> getMenus() {
        return menus;
    }

    public void setMenus(List<Respuestas> menus) {
        this.menus = menus;
    }
}