package controlador;
import java.util.HashMap;

import modelo.InstanciaJuego;
import utils.Musica;
import visual.Vista;
/**
 * Clase principal que centraliza la ejecución del juego RPG "Vacío".
 * Actúa como controlador principal: gestiona el flujo del juego,
 * muestra menús, procesa decisiones del jugador y coordina las interacciones.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
public class Vacio {
	
    public static void main(String[] args) {
    	byte play=1;
        Musica.reproducir();
        InstanciaJuego instancia= new InstanciaJuego();
        Juego juego = new Juego(instancia, new HashMap<>());
        Vista mostrar=new Vista(instancia);
        Menu.menu(play, instancia, mostrar, juego);   
    }
   

}
