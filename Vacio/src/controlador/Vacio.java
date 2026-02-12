package controlador;

import modelo.Juego;
import excepcion.Validaciones;
import modelo.Mostrar;
import modelo.Musica;

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
        // IMPRIMIR MENU1
        Juego juego = new Juego();
        Mostrar mostrar = new Mostrar(null);
        try {
            juego.inicializarEscenas();

            // Solo pedimos usuario la primera vez
            if (juego.getusuarioactual() == null) {

                juego.seleccionarOcrearUsuario();
            }

            while (true) {
                mostrar.mostrarMenuPrincipal();
                short opcion = Validaciones.obtenerOpcionValida("Elige una opción: ", (short) 1, (short) 4);

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
