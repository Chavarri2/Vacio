package controlador;

import modelo.Atributos;
import modelo.InstanciaJuego;
import modelo.Partida;
import modelo.Personaje;
import utils.LeerScanner;

public class ControladorPartida {
	public static Partida iniciarNuevaPartida(InstanciaJuego instancia) { 
    	System.out.println("\n--- Creación de personaje ---"); 
    	String nombrePersonaje = LeerScanner.leerString("Nombre del personaje: "); 
    	String genero = null; 
    	while (true) { 
    		genero = LeerScanner.leerString("Género (Hombre / Mujer / Desconocido): ").trim().toLowerCase(); 
    		if (genero.equals("hombre") || genero.equals("mujer") || genero.equals("desconocido")) { 
    			break; 
    			} 
    		System.out.println("Género no válido. Solo se permite: Hombre, Mujer o Desconocido."); 
    	} 
    	Personaje heroe = Atributos.asignarPuntos(genero, nombrePersonaje); 
    	Partida partida = new Partida(instancia.getUsuarioActual(), heroe); 
    	System.out.println("\nPartida iniciada. Prepárate para entrar al Limbo...\n"); 
    	heroe.mostrar(); 
    	return partida; 
    }
}
