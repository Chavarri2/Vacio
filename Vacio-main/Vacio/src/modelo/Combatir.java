package modelo;

import java.util.Random;
import utils.Validaciones;

/**
 * Sistema de combate dinámico y desafiante. IA del enemigo basada en vida,
 * probabilidades reales de esquiva y daño coherente basado en fuerza y defensa.
 *
 * @author Carlos
 * @author Violeta
 * @version 2.0
 * @date 14/02/2026
 */
public class Combatir {

	private Random random = new Random();
	private short vidaJugador = 0;
	private short vidaEnemigo = 0;
	private short fuerzaEnemigo = 0;
	private short fuerzaJugador = 0;
	private short velocidadJugador = 0;
	private short velocidadEnemigo = 0;

	public boolean batalla (Personaje jugador, String nombreEnemigo, String generoEnemigo, String caracteristicaEnemigo) {

		Personaje enemigo = crearEnemigo(nombreEnemigo, generoEnemigo, caracteristicaEnemigo);

		vidaJugador = (short) (jugador.getResistencia());
		vidaEnemigo = (short) (enemigo.getResistencia());
		fuerzaEnemigo = enemigo.getFuerza();
		fuerzaJugador = jugador.getFuerza();
		velocidadJugador = jugador.getVelocidad();
		velocidadEnemigo = enemigo.getVelocidad();

		System.out.println("\n¡Combate iniciado contra " + enemigo.getNombre() + "!");
		enemigo.mostrar();

		while (vidaJugador >= 0 || vidaEnemigo >= 0) {

			System.out.println("\nTu turno: Vida=" + vidaJugador + " | Enemigo Vida=" + vidaEnemigo);
			System.out.println("1. Atacar");
			System.out.println("2. Defender");

			short accionJugador = Validaciones.obtenerOpcionValida("Elige acción: ", (short) 1, (short) 2);
			short enemigoDefiende = (short) (random.nextInt(1));
			
			short danyoEnemigo = ataque(enemigoDefiende, vidaJugador, velocidadEnemigo, fuerzaEnemigo);
			short danyoJugador = ataque(accionJugador, vidaEnemigo, velocidadJugador, fuerzaJugador);
			
			if (enemigoDefiende == 1) {
				System.out.println("El enemigo adopta postura defensiva.");
				continue;
			}
			
			if (jugador.getVelocidad() < enemigo.getVelocidad()) {
				System.out.println(enemigo.getNombre()+ " Ataca primero y hace" + danyoEnemigo + " de daño");
				System.out.println(jugador.getNombre() + " Atacas y haces " + danyoJugador + " de daño");
			} else {
				System.out.println(jugador.getNombre() + " Atacas primero y haces " + danyoJugador + " de daño");
				System.out.println(enemigo.getNombre()+ " Ataca y hace" + danyoEnemigo + " de daño");
			}
		}
		return vidaEnemigo<=0;
	}

	public Personaje crearEnemigo(String nombre, String genero, String caracteristica) {

		// Estadísticas aleatorias del enemigo
		short fuerza = (short) (Math.random() * 10 + 5);
		short resistencia = (short) (Math.random() * 10 + 5);
		short velocidad = (short) (Math.random() * 10 + 5);

		Personaje enemigo = new Personaje(nombre, genero, caracteristica, fuerza, resistencia, velocidad);
		return enemigo;
	}

	public short ataque(short accion, short vidaEnemigo, short velocidad, short fuerza) {
		short danyo= (short) (fuerzaJugador / 2);
		if (accion == 1) {
			vidaEnemigo = (short) (vidaEnemigo - danyo);
			fuerza++;
		}else {
			velocidad++;
		}
		return danyo;
	}

	public void turno(short accionJugador, Personaje enemigo, Personaje jugador, short vidajugador,
			short vidaenemigo) {

	}
}
