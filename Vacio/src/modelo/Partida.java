package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa una partida completa en el juego Vacío.
 * Hereda de EntidadJuego y almacena toda la información relevante de una sesión
 * de juego.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
public class Partida extends EntidadJuego {

    /** Usuario que está jugando esta partida. */
    private Usuario usuario;

    /** Número de turnos transcurridos en la partida. */
    private Integer turnos;

    /** Resultado final de la partida (GANASTE o PERDISTE). */
    private EstadoJuego resultado;

    /** Lista de acciones realizadas por el jugador (usamos ArrayList). */
    private List<String> acciones;

    /** Personaje utilizado por el jugador en esta partida. */
    private Personaje personaje;

    /**
     * Constructor por defecto.
     * Inicializa una partida vacía con turnos en 0 y lista de acciones vacía.
     */
    public Partida() {
        super();
        this.turnos = 0;
        this.acciones = new ArrayList<>();
    }

    /**
     * Constructor con usuario.
     * Útil cuando se inicia una partida sin personaje definido aún.
     *
     * @param usuario El usuario que juega esta partida
     */
    public Partida(Usuario usuario) {
        super();
        this.usuario = usuario;
        this.turnos = 0;
        this.acciones = new ArrayList<>();
    }

    /**
     * Constructor completo con usuario y personaje.
     * Es el constructor más usado al iniciar una partida.
     *
     * @param usuario   El usuario que juega
     * @param personaje El personaje creado por el usuario
     */
    public Partida(Usuario usuario, Personaje personaje) {
        super();
        this.usuario = usuario;
        this.personaje = personaje;
        this.turnos = 0;
        this.acciones = new ArrayList<>();
    }

    /**
     * Registra una acción realizada por el jugador en la partida.
     *
     * @param accion Descripción de la acción (ej: "Avanzar hacia la luz")
     */
    public void registrarAcciones(String accion) {
        if (accion != null && !accion.trim().isEmpty()) {
            acciones.add(accion.trim());
        }
    }

    // Incrementa en 1 el contador de turnos de la partida.
    public void incrementarTurno() {
        turnos++;
    }

    /**
     * Calcula la puntuación final de la partida.
     * La puntuación se basa en:
     * - 10 puntos por cada turno sobrevivido
     * - Bono de 300 puntos si se gana (escapar del limbo)
     *
     * @return Objeto Puntuacion con puntos calculados y comentario asociado
     */
    public Puntuacion getPuntuacion() {
        short puntosBase = (short) (turnos * 10);
        short bonoVictoria = (short) ((resultado == EstadoJuego.GANASTE) ? 300 : 0);
        short puntosTotales = (short) (puntosBase + bonoVictoria);

        String comentario = (resultado == EstadoJuego.GANASTE)
                ? "¡Victoria! Has escapado del limbo con éxito."
                : "Derrota en el limbo.";

        return new Puntuacion(usuario, puntosTotales, comentario);
    }

    // Getters y Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getTurnos() {
        return turnos;
    }

    public void setTurnos(Integer turnos) {
        this.turnos = turnos;
    }

    public EstadoJuego getResultado() {
        return resultado;
    }

    public void setResultado(EstadoJuego resultado) {
        this.resultado = resultado;
    }

    public List<String> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<String> acciones) {
        this.acciones = acciones;
    }

    public Personaje getPersonaje() {
        return personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }
}