package modelo;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.JsonNode;
import excepcion.Excepciones;
import utils.LeerScanner;
import utils.Utilidades;
import utils.Validaciones;
import java.util.ArrayList;
import java.util.List;
import modelo.Juego;

public class Juego {
	private static Map<String, Escena> escenas = new HashMap<>();
	private static Partida partidaActual;
	private static boolean juegoEnCurso = false;
	private static List<Usuario> usuarios = new ArrayList<>();
	private final InstanciaJuego instancia;
	private Vista vista;

	public Juego(InstanciaJuego instancia) {
		this.instancia = instancia;
		instancia.setTexto(Utilidades.leerArchivo());
	}

	public void inicializarEscenas() {
		// ESCENA escena_empezar
		JsonNode escenaInicio = instancia.getTexto().path("EVENTOS").path("CAP1").path("ESCENAINICIAL");
		Escena inicio = new Escena(escenaInicio.path("NOMBRE").asText(), escenaInicio.path("TEXTO").asText());

		// RESPUESTAS escena_empezar
		JsonNode opcionesEscenaInicio = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMIENZO");
		for (JsonNode Item : opcionesEscenaInicio) {
			inicio.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		// GUARDAR escena_empezar
		escenas.put(escenaInicio.path("ID").asText(), inicio);

	}

	public void crearNuevoUsuario() throws Excepciones {
		System.out.println("\nBienvenid@ al Limbo...");

		String nombre = null;
		String nickname = null;
		short edad = 0;

		nombre= Validaciones.ingresarNombre(nombre, "Ingresa tu nombre real", "^[A-Za-zÁÉÍÓÚÜáéíóúüÑñ ]{2,50}$","Tu nombre solo puede contener letras de la A a la Z y debe tener de 2-50 caracteres");
		nickname= Validaciones.ingresarNombre(nickname, "Ingresa tu nickname", "^[\\w\\-\\. ]{3,20}$", "El nickname puede contener letras, números, guiones, puntos y espacios (3-20 caracteres). Intenta de nuevo.");
		edad=Validaciones.obtenerOpcionValida("Introduce tu edad (13-120 años): ",(short) 13, (short) 120);
		
		instancia.setUsuarioActual(new Usuario(nombre, nickname, edad));
		usuarios.add(instancia.getUsuarioActual());
		System.out.println("\n¡Bienvenid@, " + nickname + "!");
	}

	public Personaje AsignarPuntos(String genero, String nombrePersonaje) {
		// Característica principal eliminada por completo

		short fuerza = 5;
		short resistencia = 5;
		short velocidad = 5;
		short puntosRestantes = 10;
		short stat = 1;
		short cantidad = 0;

		System.out.println("\nAtributos base: Fuerza=5, Resistencia=5, Velocidad=5");
		System.out.println("Tienes 10 puntos para distribuir.");

		do {

			System.out.println("\nPuntos restantes: " + puntosRestantes);
			System.out.println("Estadisticas Actuales: Fuerza: " + fuerza + ", Resistencia: " + resistencia
					+ ", Velocidad: " + velocidad + "\n");
			System.out
					.println("0. Empezar\n" + "1. Fuerza\n" + "2. Resistencia\n" + "3. Velocidad\n" + "4. Reiniciar\n");

			if (puntosRestantes >= 0) {
				stat = Validaciones.obtenerOpcionValida("Selecciona un atributo a modificar: ", (short) 0, (short) 4);

				if (stat != 4 && stat != 0 && puntosRestantes != 0 && puntosRestantes > 0) {
					cantidad = Validaciones.obtenerOpcionValida("¿Cuántos puntos gastar (1-" + puntosRestantes + ")? ",
							(short) 1, puntosRestantes);
				}
			}
			switch (stat) {
			case 0:
				puntosRestantes = -1;
				System.out.println("Ha finalizado la personalizacion de las estadisticas");
				break;
			case 1:
				if (puntosRestantes > 0) {
					fuerza += cantidad;
					puntosRestantes -= cantidad;
				} else {
					System.out.println("Puntos insuficientes");
				}
				break;
			case 2:
				if (puntosRestantes > 0) {
					resistencia += cantidad;
					puntosRestantes -= cantidad;
				} else {
					System.out.println("Puntos insuficientes");
				}
				break;
			case 3:
				if (puntosRestantes > 0) {
					velocidad += cantidad;
					puntosRestantes -= cantidad;
				} else {
					System.out.println("Puntos insuficientes");
				}
				break;
			case 4:
				fuerza = 5;
				resistencia = 5;
				velocidad = 5;
				puntosRestantes = 10;
				break;
			default:
				System.out.println("¡Opción invalida! intente de nuevo");
				break;
			}
		} while (puntosRestantes >= 0);
		Personaje heroe = new Personaje(nombrePersonaje, genero, fuerza, resistencia, velocidad);
		heroe.setCaracteristica("Neutral");
		return heroe;
	}

	public void iniciarNuevaPartida() {
		System.out.println("\n--- Creación de personaje ---");

		String nombrePersonaje = LeerScanner.leerString("Nombre del personaje: ");

		String genero = null;
		while (true) {
			genero = LeerScanner.leerString("Género (Hombre / Mujer / Desconocido): ").trim();
			genero = genero.toLowerCase();
			if (genero.equals("hombre") || genero.equals("mujer") || genero.equals("desconocido")) {
				break;
			}
			System.out.println("Género no válido. Solo se permite: Hombre, Mujer o Desconocido.");
		}

		// Característica principal eliminada por completo

		Personaje heroe = AsignarPuntos(genero, nombrePersonaje);

		partidaActual = new Partida(instancia.getUsuarioActual(), heroe);
		juegoEnCurso = true;

		System.out.println("\nPartida iniciada. Prepárate para entrar al Limbo...\n");
		heroe.mostrar();

		jugarPartida(instancia.getTexto().path("EVENTOS").path("CAP1").path("ESCENAINICIAL").path("ID").asText());
	}

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

					victoria = Combatir.combate(partidaActual.getPersonaje());

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

	public void seleccionarOcrearUsuario() throws Excepciones {
		System.out.println("\n=== Usuarios disponibles ===");
		if (usuarios.isEmpty()) {
			System.out.println("No hay usuarios creados aún.");
		} else {
			for (short i = 0; i < usuarios.size(); i++) {
				Usuario u = usuarios.get(i);
				System.out.printf("%d. %s (%s)%n", i + 1, u.getNickname(), u.getNombre());
			}
		}
		System.out.println((usuarios.size() + 1) + ". Crear nuevo usuario");
		System.out.println("============================");

		short opcion = Validaciones.obtenerOpcionValida("Selecciona una opcion: ", (short) 1,
				(short) (usuarios.size() + 1));

		if (opcion == usuarios.size() + 1) {
			crearNuevoUsuario();
		} else {
			instancia.setUsuarioActual(usuarios.get(opcion - 1));
			System.out.println("\nUsuario seleccionado: " + instancia.getUsuarioActual().getNickname());
		}
	}
}
