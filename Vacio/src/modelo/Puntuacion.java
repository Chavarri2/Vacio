// Puntuacion.java
/**
 * Clase que representa la puntuación de un usuario.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pizco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

public class Puntuacion {
    /** Usuario asociado. */
    private Usuario usuario;
    
    /** Puntos obtenidos. */
    private Integer puntos;
    
    /** Comentario opcional. */
    private String comentario;

    /**
     * Constructor por defecto.
     */
    public Puntuacion() {
    }

    /**
     * Constructor con usuario y puntos.
     * @param usuario Usuario.
     * @param puntos Puntos.
     */
    public Puntuacion(Usuario usuario, Integer puntos) {
        this.usuario = usuario;
        this.puntos = puntos;
    }

    /**
     * Constructor completo.
     * @param usuario Usuario.
     * @param puntos Puntos.
     * @param comentario Comentario.
     */
    public Puntuacion(Usuario usuario, Integer puntos, String comentario) {
        this.usuario = usuario;
        this.puntos = puntos;
        this.comentario = comentario;
    }

    // Getters y Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getPuntos() {
        return puntos;
    }

    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}