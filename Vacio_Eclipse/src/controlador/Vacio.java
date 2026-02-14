package controlador;
import modelo.InstanciaJuego;
import modelo.Juego;
import modelo.Mostrar;
import modelo.Musica;
import utils.Validaciones;

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
    	
    	Musica.reproducir();
        byte PLAY=1;
        InstanciaJuego instancia= new InstanciaJuego();

        Juego juego = new Juego(instancia);
        Mostrar mostrar = new Mostrar(instancia);
        try {
            // IMPRIMIR MENU1
            mostrar.mostrarTituloPrincipal();
            juego.inicializarEscenas();

            // Solo pedimos usuario la primera vez
            if (instancia.getUsuarioActual() == null) {
                juego.seleccionarOcrearUsuario();
            }

            while (true) {
                mostrar.mostrarMenuPrincipal();
                short opcion = Validaciones.obtenerOpcionValida("Elige una opción: ", (short) 1, (short) 6);

                switch (opcion) {
                    case 1:
                        juego.iniciarNuevaPartida();
                        break;
                    case 2:
                        mostrar.mostrarPartidasAnteriores();
                        break;
                    case 3:
                        mostrar.mostrarPuntuaciones();
                        break;
                    case 4:
                    	if(PLAY==1) {
                    		Musica.pausar();
                    		PLAY=0;
                    	}else {
                    		System.out.println("La musica ya esta pausada");
                    	}
                    	break;
                    case 5:
                    	if(PLAY==0) {
                    		Musica.reproducir();
                    		PLAY=1;
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
