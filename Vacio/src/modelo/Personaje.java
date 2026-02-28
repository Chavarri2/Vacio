package modelo;

import visual.Interactuar;

/**
 * Representa al personaje controlado por el jugador en el juego Vacío.
 * Hereda de EntidadJuego para tener ID automático y nombre.
 * Implementa la interfaz Interactuar para mostrar su estado.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
public class Personaje extends EntidadJuego implements Interactuar {

    private String genero; // Hombre, Mujer, Desconocido
    private String caracteristica;
    private short fuerza;
    private short resistencia;
    private short velocidad;

    /**
     * Constructor vacío (necesario para serialización y uso genérico)
     */
    public Personaje() {
        super(); // Llama al constructor de EntidadJuego
        this.genero = "Desconocido";
        this.fuerza = 5;
        this.resistencia = 5;
        this.velocidad = 5;
    }

    /**
     * Constructor completo con todos los atributos
     *
     * @param nombre      Nombre del personaje
     * @param genero      Género (Hombre, Mujer, Desconocido)
     * @param fuerza      Valor de fuerza
     * @param resistencia Valor de resistencia
     * @param velocidad   Valor de velocidad
     */
    public Personaje(String nombre, String genero, short fuerza, short resistencia, short velocidad) {
        super(nombre); // Asigna nombre y genera ID automático
        this.genero = genero;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.velocidad = velocidad;
    }

    /**
     * Constructor completo con todos los atributos.
     * 
     * @param nombre         Nombre del personaje.
     * @param genero         Género.
     * @param caracteristica Característica narrativa.
     * @param fuerza         Fuerza.
     * @param resistencia    Resistencia.
     * @param velocidad      Velocidad.
     */
    public Personaje(String nombre, String genero, String caracteristica, short fuerza, short resistencia,
            short velocidad) {
        super(nombre);
        this.genero = genero;
        this.caracteristica = caracteristica;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
        this.velocidad = velocidad;
    }

    // Getters y Setters

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public short getFuerza() {
        return fuerza;
    }

    public void setFuerza(short fuerza) {
        this.fuerza = fuerza;
    }

    public short getResistencia() {
        return resistencia;
    }

    public void setResistencia(short resistencia) {
        this.resistencia = resistencia;
    }

    public short getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(short velocidad) {
        this.velocidad = velocidad;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    /**
     * Muestra la información del personaje por consola.
     * Método requerido por la interfaz Interactuar.
     */
    @Override
    public void mostrar() {
        System.out.println("Personaje: " + nombre + ", Género: " + genero + ", Característica: " + caracteristica);
        System.out.println(
                "Atributos - Fuerza: " + fuerza + ", Resistencia: " + resistencia + ", Velocidad: " + velocidad);
    }

}
