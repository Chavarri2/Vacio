
/**
 * Clase auxiliar con métodos estáticos para manejar atributos de personajes.
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

import utils.Validaciones;

public class Atributos {
	
    public static void aumentarFuerza(Personaje p, short cantidad) {
        p.setFuerza((short) (p.getFuerza() + cantidad));
    }

    public static void aumentarResistencia(Personaje p, short cantidad) {
        p.setResistencia((short) (p.getResistencia() + cantidad));
    }

    public static void aumentarAgilidad(Personaje p, short cantidad) {
        p.setVelocidad((short) (p.getVelocidad() + cantidad));
    }

    public static void reducirFuerza(Personaje p, short cantidad) {
        p.setFuerza((short) (p.getFuerza() - cantidad));
    }

    public static void reducirResistencia(Personaje p, short cantidad) {
        p.setResistencia((short) (p.getResistencia() - cantidad));
    }

    public static void reducirAgilidad(Personaje p, short cantidad) {
        p.setVelocidad((short) (p.getVelocidad() - cantidad));
    }

    public static void mostrarAtributos(Personaje p) {
        p.mostrar();
    }
    public static Personaje asignarPuntos(String genero, String nombrePersonaje) {
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
}