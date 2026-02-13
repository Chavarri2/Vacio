// EntidadJuego.java
/**
 * Clase abstracta que representa una entidad base en el juego, proporcionando un ID único y un nombre.
 * Todas las entidades del juego heredan de esta clase para mantener consistencia en la identificación.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

public abstract class EntidadJuego {
    /** Contador estático para generar IDs únicos. */
    private static Integer contadorIds = 0;

    /** ID único de la entidad. */
    protected Integer id;

    /** Nombre de la entidad. */
    protected String nombre;

    /**
     * Constructor por defecto que asigna un ID único.
     */
    public EntidadJuego() {
        this.id = ++contadorIds;
    }

    /**
     * Constructor que asigna un ID único y un nombre.
     * 
     * @param nombre El nombre de la entidad.
     */
    public EntidadJuego(String nombre) {
        this.id = ++contadorIds;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + nombre;
    }
}