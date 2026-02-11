// Usuario.java
/**
 * Clase que representa al usuario real que juega.
 * Hereda de EntidadJuego.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pizco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

import java.util.LinkedList;
import java.util.List;

public class Usuario extends EntidadJuego {
    /** Nickname del usuario. */
    private String nickname;
    
    /** Edad del usuario. */
    private Integer edad;
    
    /** Lista de partidas asociadas (LinkedList). */
    
    private List<Partida> partidas = new LinkedList<>();

    /**
     * Constructor por defecto.
     */
    public Usuario() {
        super();
        this.partidas = new LinkedList<>();
    }

    /**
     * Constructor con nombre y nickname.
     * @param nombre Nombre real.
     * @param nickname Nickname.
     */
    public Usuario(String nombre, String nickname) {
        super(nombre);
        this.nickname = nickname;
        this.partidas = new LinkedList<>();
    }

    /**
     * Constructor completo.
     * @param nombre Nombre real.
     * @param nickname Nickname.
     * @param edad Edad.
     */
    public Usuario(String nombre, String nickname, Integer edad) {
        super(nombre);
        this.nickname = nickname;
        this.edad = edad;
        this.partidas = new LinkedList<>();
    }

    // Getters y Setters
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }
}