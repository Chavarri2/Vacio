// Usuario.java
/**
 * Clase que representa al usuario real que juega.
 * Hereda de EntidadJuego.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package com.vacio.modelo;

import java.util.LinkedList;
import java.util.List;

public class Usuario extends EntidadJuego {
    /** Nickname del usuario. */
    private String nickname;

    /** Edad del usuario. */
    private short edad;

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
     * 
     * @param nombre   Nombre real.
     * @param nickname Nickname.
     */
    public Usuario(String nombre, String nickname) {
        super(nombre);
        this.nickname = nickname;
        this.partidas = new LinkedList<>();
    }

    /**
     * Constructor completo.
     * 
     * @param nombre   Nombre real.
     * @param nickname Nickname.
     * @param edad     Edad.
     */
    public Usuario(String nombre, String nickname, short edad) {
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

    public short getEdad() {
        return edad;
    }

    public void setEdad(short edad) {
        this.edad = edad;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }
}