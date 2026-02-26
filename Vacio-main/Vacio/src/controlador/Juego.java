package controlador;

import java.util.HashMap;
import java.util.Map;
import controlador.Juego;
import excepcion.Excepciones;
import modelo.Combatir;
import modelo.Escena;
import modelo.EstadoJuego;
import modelo.InstanciaJuego;
import modelo.Partida;
import modelo.Puntuacion;
import modelo.Respuestas;
import modelo.Usuario;
import utils.Utilidades;
import utils.Validaciones;
import java.util.ArrayList;
import java.util.List;

public class Juego {
	//Atributos
	//Constantes y encapsulados
	
	private final InstanciaJuego instancia;
	//Encapsulados
	private Map<String, Escena> escenas = new HashMap<>();
	private static Partida partidaActual;
	private static boolean juegoEnCurso = false;
	private static List<Usuario> usuarios = new ArrayList<>();
	
	public Juego(InstanciaJuego instancia, Map<String, Escena> escenas) {
	    this.instancia = instancia;
	    this.escenas = escenas;
	    instancia.setTexto(Utilidades.leerArchivo());
	}
	
	public void playJuego(InstanciaJuego instancia) throws Excepciones {

	    // Inicializar escenas
	    ControladorEscena.inicializarEscenas(instancia, escenas);

	    // Seleccionar o crear usuario
	    ControladorUsuario.seleccionarOcrearUsuario(instancia, usuarios);

	    // Iniciar nueva partida
	    partidaActual = ControladorPartida.iniciarNuevaPartida(instancia);
	    juegoEnCurso = true;

	    // Obtener clave inicial
	    String claveInicial = instancia.getTexto()
	        .path("EVENTOS").path("CAP1").path("ESCENAINICIAL").path("ID").asText();

	    // Jugar primera escena
	    jugarPartida(claveInicial);
	    
	    
	}

	//Genera la sucesión de escenas de el juego
	
	public void jugarPartida(String claveEscenaActual) {
		try {
			while (juegoEnCurso) {
				Escena escenaActual = escenas.get(claveEscenaActual);
				if (escenaActual == null) {
					System.out.println("Escena no encontrada. Volviendo al inicial.");
					claveEscenaActual = instancia.getTexto().path("EVENTOS").path("CAP1").path("ESCENAINICIAL")
							.path("ID").asText();
					continue;
					
				}
				Utilidades.typeWriter("\n" + escenaActual.getDescripcion(), 10);
				
				System.out.println("Opciones:");
				short index = 1;
				for (Respuestas m : escenaActual.getMenus()) {
					System.out.println(index + ". " + m.getTexto());
					index++;
				}

				short eleccion = Validaciones.obtenerOpcionValida("Tu decisión: ", (short) 1,
						(short) escenaActual.getMenus().size());

				Respuestas menuElegido = escenaActual.getMenus().get(eleccion - 1);
				String accionElegida = menuElegido.getTexto();
				String siguienteClave = menuElegido.getClave();

				partidaActual.registrarAcciones(accionElegida);
				partidaActual.incrementarTurno();

				System.out.println("Has elegido: " + accionElegida);

				if (siguienteClave.equals("combate")) {
					boolean victoria = false;
					Combatir combate = new Combatir();
					victoria = combate.batalla(partidaActual.getPersonaje(), "Caronte", "Hombre", "Hostil");

					if (victoria) {
						siguienteClave = "escena_final_bueno";
					} else {
						siguienteClave = "limbo_inicial";
						System.out.println("¡Has sido derrotado, pero puedes intentarlo de nuevo desde el inicio!");
					}
				}

				if (siguienteClave.contains("final_bueno")) {
					partidaActual.setResultado(EstadoJuego.GANASTE);
					juegoEnCurso = false;
					System.out.println("\n¡Has escapado del limbo! FINAL BUENO.");
				} else {
					claveEscenaActual = siguienteClave;
				}

				System.out.println("\nEstado actual del personaje:");
				partidaActual.getPersonaje().mostrar();
			}

			// Final de partida
			Puntuacion puntuacion = partidaActual.getPuntuacion();
			System.out.println("\nPuntuación obtenida: " + puntuacion.getPuntos());

			System.out.println("\nPartida finalizada.");
			System.out.println("Turnos: " + partidaActual.getTurnos());
			System.out.println("Resultado: " + partidaActual.getResultado());
			System.out.println("Acciones realizadas: " + partidaActual.getAcciones().size());

			instancia.getUsuarioActual().getPartidas().add(partidaActual);

		} catch (Exception e) {
			System.out.println("Error inesperado durante la partida: " + e.getMessage());
		}
	}
	public Map<String, Escena> getEscenas() {
	    return escenas;
	}
	public List<Usuario> getUsuarios() {
	    return usuarios;
	}

	
}
