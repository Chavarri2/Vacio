package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import utils.Validaciones;

/**
 * Sistema de combate dinámico y desafiante. IA del enemigo basada en vida,
 * probabilidades reales de esquiva y daño coherente basado en fuerza y defensa.
 *
 * @author Carlos
 * @author Violeta Pisco Aznar
 * @version 2.0
 * @date 14/02/2026
 */
public class Combatir {

	private Random random = new Random();
	List<Short> atributos= new ArrayList<Short>();
	
	public void guardarAtributos (Personaje personaje) {
		atributos.add(personaje.getVelocidad());
		atributos.add(personaje.getFuerza());
		atributos.add(personaje.getResistencia());
	}
	public void resetearPersonaje (Personaje personaje) {
		personaje.setVelocidad(atributos.get(0));
		personaje.setFuerza(atributos.get(1));
		personaje.setResistencia(atributos.get(2));
	}

	public boolean batalla (Personaje jugador, String nombreEnemigo, String generoEnemigo, String caracteristicaEnemigo) {
		
		guardarAtributos(jugador);
		Personaje enemigo = crearEnemigo(nombreEnemigo, generoEnemigo, caracteristicaEnemigo);

		System.out.println("\n¡Combate iniciado contra " + enemigo.getNombre() + "!");
		enemigo.mostrar();

		while (jugador.getResistencia() > 0 && enemigo.getResistencia() > 0) {

			System.out.println("\nTu turno: Vida=" + jugador.getResistencia() + " | Enemigo Vida=" + enemigo.getResistencia());
			System.out.println("1. Atacar");
			System.out.println("2. Defender");

			short accionJugador = Validaciones.obtenerOpcionValida("Elige acción: ", (short) 1, (short) 2);
			short accionEnemigo = (short) (random.nextInt(2) + 1);
			
			turno(jugador, enemigo, accionJugador,accionEnemigo);
		}
		
		resetearPersonaje(jugador);
		
		return enemigo.getResistencia()<=0;
	}

	public Personaje crearEnemigo(String nombre, String genero, String caracteristica) {

		// Estadísticas aleatorias del enemigo
		short fuerza = (short) (Math.random() * 10 + 5);
		short resistencia = (short) (Math.random() * 10 + 5);
		short velocidad = (short) (Math.random() * 10 + 5);

		Personaje enemigo = new Personaje(nombre, genero, caracteristica, fuerza, resistencia, velocidad);
		return enemigo;
	}
	//Resta vida y mejora caracteristicas en base elecciones
	public void golpe(short accionJugador, Personaje atacante, Personaje defensor, short accionDefensor) {
		short danyo= 0;
		if (accionJugador == 1 && accionDefensor==1) {
			danyo= (short) (atacante.getFuerza()/ 2);
			defensor.setResistencia((short) (defensor.getResistencia() - danyo));
			atacante.setFuerza((short)(atacante.getFuerza()+1));
		}else {
			atacante.setVelocidad((short)(atacante.getVelocidad()+1));
		}
		if(accionJugador==1) {
			System.out.println(atacante.getNombre() + " ataca y hace " + danyo + " de daño");
		}else {
			System.out.println(atacante.getNombre() + " adopta postura defensiva.");
		}
	}
	//Orden de cada turno
	public void turno(Personaje jugador, Personaje enemigo, short accionJugador, short accionEnemigo) {

	    boolean jugadorPrimero = jugador.getVelocidad() >= enemigo.getVelocidad();
	    //Asigna quien ataca primero
	    Personaje primero     = jugadorPrimero ? jugador : enemigo;
	    Personaje segundo     = jugadorPrimero ? enemigo : jugador;

	    short accionPrimero   = jugadorPrimero ? accionJugador : accionEnemigo;
	    short accionSegundo   = jugadorPrimero ? accionEnemigo : accionJugador;

	    //Ejecuta los ataque en orden
	    golpe(accionPrimero, primero, segundo, accionSegundo);

	    //Si el segundo en atacar tiene vida : realiza accion
	    if (segundo.getResistencia() > 0) {
	        golpe(accionSegundo, segundo, primero, accionPrimero);
	    }
	}
}
