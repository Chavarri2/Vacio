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

	    // Iniciar nueva partida - Pepe
	    //partidaActual=ControladorPartida.TestNewPartida(instancia);
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
				//Cambia cada "@" de el json por la letra correspondiente dependiendo de el genero de tu personaje
				String textoEscena= Utilidades.cambiarGenero(escenaActual.getDescripcion(), partidaActual.getPersonaje().getGenero());
				
				//Escribe la escena
				Utilidades.typeWriter("\n" + textoEscena, 10);
				
				//Escribe las opciones disponibles de cada escena
				System.out.println("Opciones:");
				short index = 1;
				for (Respuestas m : escenaActual.getMenus()) {
					System.out.println(index + ". " + m.getTexto());
					index++;
				}
				
				//Excepcion: Valida que insertes un numero correcto
				short eleccion = Validaciones.obtenerOpcionValida("Tu decisión: ", (short) 1,
				(short) escenaActual.getMenus().size());
				
				//Registrar acción 
				Respuestas menuElegido = escenaActual.getMenus().get(eleccion - 1);
				String accionElegida = menuElegido.getTexto();
				String siguienteClave = menuElegido.getClave();
				
				//Incrementar turno (para la puntuación)
				partidaActual.registrarAcciones(accionElegida);
				partidaActual.incrementarTurno();
				
				//Imprime cual ha sido la decisión
				System.out.println("Has elegido: " + accionElegida);
				
				//Comienza el combate
				if (siguienteClave.equals("combate")) {
					boolean victoria = false;
					Combatir combate = new Combatir();
					victoria = combate.batalla(partidaActual.getPersonaje(), "Aurelia", "Mujer", "Hostil");

					if (victoria) {
						siguienteClave = "final_demo";
					} else {
						siguienteClave = "final_perdido";
					}
				}
				
				//Cambia parametros, finaliza el juego e imprime por pantalla los resultados de la partida
				if (siguienteClave.contains("null")) {
					partidaActual.setResultado(EstadoJuego.PERDISTE);
					juegoEnCurso = false;
					System.out.println("\n¡Has perdido! FINAL MALO.");
				} else if(siguienteClave.contains("final_demo")){
					partidaActual.setResultado(EstadoJuego.GANASTE);
					juegoEnCurso = false;
					System.out.println("Has completado la Demo ¡Enhorabuena!");
				}
				else {
					claveEscenaActual = siguienteClave;
				}

				System.out.println("\nEstado actual del personaje:");
				partidaActual.getPersonaje().mostrar();
			}

			// Crea las puntuaciones
			Puntuacion puntuacion = partidaActual.getPuntuacion();
			System.out.println("\nPuntuación obtenida: " + puntuacion.getPuntos());

			System.out.println("\nPartida finalizada.");
			System.out.println("Turnos: " + partidaActual.getTurnos());
			System.out.println("Resultado: " + partidaActual.getResultado());
			System.out.println("Acciones realizadas: " + partidaActual.getAcciones().size());

			instancia.getUsuarioActual().getPartidas().add(partidaActual);
			
			//Posibles errores
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
