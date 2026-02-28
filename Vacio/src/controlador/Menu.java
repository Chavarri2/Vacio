package controlador;

import modelo.InstanciaJuego;
import utils.Musica;
import utils.Validaciones;
import visual.Vista;

public class Menu {
	
	private Menu() {}  // ← evita que alguien cree instancias por accidente

	 public static void menu(byte play, InstanciaJuego instancia, Vista mostrar, Juego juego) {
	    	try {
	            // IMPRIMIR MENU1
	            mostrar.mostrarTituloPrincipal();
	            ControladorEscena.inicializarEscenas(instancia, juego.getEscenas());
	            
	            //ControladorUsuario.TestCrearUsuario(instancia, juego.getUsuarios()); //Pepe
	            // Solo pedimos usuario la primera vez
	            if (instancia.getUsuarioActual() == null) {
	            	ControladorUsuario.seleccionarOcrearUsuario(instancia, juego.getUsuarios());
	            }

	            while (true) {
	                mostrar.mostrarMenuPrincipal();
	                short opcion = Validaciones.obtenerOpcionValida("Elige una opción: ", (short) 1, (short) 6);

	                switch (opcion) {
	                    case 1:
	                    	juego.playJuego(instancia);
	                        break;
	                    case 2:
	                        mostrar.mostrarPartidasAnteriores();
	                        break;
	                    case 3:
	                        mostrar.mostrarPuntuaciones();
	                        break;
	                    case 4:
	                    	if(play==1) {
	                    		Musica.pausar();
	                    		play=0;
	                    	}else {
	                    		System.out.println("La musica ya esta pausada");
	                    	}
	                    	break;
	                    case 5:
	                    	if(play==0) {
	                    		Musica.reproducir();
	                    		play=1;
	                    	}else {
	                    		System.out.println("\nLa musica ya se esta reproduciendo\n");
	                    	}
	                        break;
	                    case 6:
	                        System.out.println("\n¡Gracias por jugar a Vacío!");
	                        System.out.println("Hasta la próxima...");
	                        return;
	                    default:
	                        System.out.println("Opción no reconocida.");
	                }
	            }

	        } catch (Exception e) {
	            System.out.println("Error grave en la ejecución del juego: " + e.getMessage());
	        }
	    }
}
