package controlador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.InputMismatchException;

import excepcion.NombreInvalidoExcepcion;
import modelo.Atributos;
import modelo.Escena;
import modelo.EstadoJuego;
import modelo.Menu;
import modelo.Partida;
import modelo.Personaje;
import modelo.Puntuacion;
import modelo.Usuario;
import utils.LeerScanner;

/**
 * Clase principal que centraliza la ejecución del juego RPG "Vacío".
 * Actúa como controlador principal: gestiona el flujo del juego,
 * muestra menús, procesa decisiones del jugador y coordina las interacciones.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pizco
 * @version 1.0
 * @since 2026-02-11
 */
public class Vacio {

    private static List<Usuario> usuarios = new ArrayList<>();
    private static Usuario usuarioActual = null; // Se mantiene hasta salir
    private static Partida partidaActual;
    private static boolean juegoEnCurso = false;
    private static Map<String, Escena> escenas = new HashMap<>();
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("=====================================");
        System.out.println("          V A C I O   -   RPG        ");
        System.out.println("  Un viaje entre la vida y la muerte  ");
        System.out.println("=====================================\n");

        try {
            inicializarEscenas();

            // Solo pedimos usuario la primera vez
            if (usuarioActual == null) {
                seleccionarOcrearUsuario();
            }

            while (true) {
                mostrarMenuPrincipal();
                int opcion = obtenerOpcionValida("Elige una opción: ", 1, 4);

                switch (opcion) {
                    case 1:
                        iniciarNuevaPartida();
                        break;
                    case 2:
                        mostrarPartidasAnteriores();
                        break;
                    case 3:
                        mostrarPuntuaciones();
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

    private static void inicializarEscenas() {
        Escena limboInicial = new Escena("Limbo Inicial", "Te encuentras en un vasto vacío gris. Delante ves tres caminos...");
        limboInicial.getMenus().add(new Menu("1. Avanzar hacia la luz tenue.", "escena_luz"));
        limboInicial.getMenus().add(new Menu("2. Retroceder hacia la oscuridad.", "escena_oscuridad"));
        limboInicial.getMenus().add(new Menu("3. Gritar pidiendo ayuda.", "escena_grito"));
        escenas.put("limbo_inicial", limboInicial);

        Escena escenaLuz = new Escena("Camino de Luz", "La luz te envuelve, sientes una energía revitalizante... pero ¿es una trampa?");
        escenaLuz.getMenus().add(new Menu("1. Absorber la luz (aumenta resistencia).", "escena_final_bueno"));
        escenaLuz.getMenus().add(new Menu("2. Huir de la luz.", "limbo_inicial"));
        escenas.put("escena_luz", escenaLuz);

        Escena escenaOscuridad = new Escena("Camino de Oscuridad", "La oscuridad te consume, sientes debilidad... pero quizás haya un secreto.");
        escenaOscuridad.getMenus().add(new Menu("1. Explorar la oscuridad (riesgo de perder fuerza).", "escena_secreto"));
        escenaOscuridad.getMenus().add(new Menu("2. Volver atrás.", "limbo_inicial"));
        escenas.put("escena_oscuridad", escenaOscuridad);

        Escena escenaGrito = new Escena("Eco del Grito", "Tu grito resuena... algo se acerca.");
        escenaGrito.getMenus().add(new Menu("1. Esperar (evento aleatorio).", "escena_encuentro"));
        escenaGrito.getMenus().add(new Menu("2. Correr.", "limbo_inicial"));
        escenas.put("escena_grito", escenaGrito);

        Escena escenaSecreto = new Escena("Secreto en la Oscuridad", "Encuentras un artefacto antiguo que aumenta tu velocidad.");
        escenaSecreto.getMenus().add(new Menu("1. Tomar el artefacto.", "limbo_inicial"));
        escenaSecreto.getMenus().add(new Menu("2. Ignorarlo.", "escena_oscuridad"));
        escenas.put("escena_secreto", escenaSecreto);

        Escena escenaEncuentro = new Escena("Encuentro Misterioso", "Una entidad hostil aparece... ¡prepárate para combatir!");
        escenaEncuentro.getMenus().add(new Menu("1. Atacar (usa fuerza).", "combate"));
        escenaEncuentro.getMenus().add(new Menu("2. Defender (usa resistencia).", "combate"));
        escenaEncuentro.getMenus().add(new Menu("3. Huir (usa velocidad).", "limbo_inicial"));
        escenas.put("escena_encuentro", escenaEncuentro);

        Escena finalBueno = new Escena("Final Bueno", "Has escapado del limbo con vida. ¡Victoria!");
        escenas.put("escena_final_bueno", finalBueno);
    }

    private static void seleccionarOcrearUsuario() throws NombreInvalidoExcepcion {
        System.out.println("\n=== Usuarios disponibles ===");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios creados aún.");
        } else {
            for (int i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                System.out.printf("%d. %s (%s)%n", i + 1, u.getNickname(), u.getNombre());
            }
        }
        System.out.println((usuarios.size() + 1) + ". Crear nuevo usuario");
        System.out.println("============================");

        int opcion = obtenerOpcionValida("Selecciona un usuario: ", 1, usuarios.size() + 1);

        if (opcion == usuarios.size() + 1) {
            crearNuevoUsuario();
        } else {
            usuarioActual = usuarios.get(opcion - 1);
            System.out.println("\nUsuario seleccionado: " + usuarioActual.getNickname());
        }
    }

    private static void crearNuevoUsuario() throws NombreInvalidoExcepcion {
        System.out.println("\nBienvenid@ al Limbo...");

        String nombre = null;
        while (true) {
            nombre = LeerScanner.leerString("Introduce tu nombre real: ").trim();
            if (nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]{2,50}$")) {
                break;
            }
            System.out.println("El nombre solo puede contener letras y espacios (2-50 caracteres). Intenta de nuevo.");
        }

        String nickname = null;
        while (true) {
            nickname = LeerScanner.leerString("Elige un nickname: ").trim();
            if (nickname.matches("^[\\w\\-\\. ]{3,20}$")) {
                break;
            }
            System.out.println("El nickname puede contener letras, números, guiones, puntos y espacios (3-20 caracteres). Intenta de nuevo.");
        }

        int edad = 0;
        while (edad < 13 || edad > 120) {
            edad = leerEnteroSeguro("Introduce tu edad (13-120 años): ");
        }

        usuarioActual = new Usuario(nombre, nickname, edad);
        usuarios.add(usuarioActual);
        System.out.println("\n¡Bienvenid@, " + nickname + "!");
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Nueva partida");
        System.out.println("2. Ver partidas anteriores");
        System.out.println("3. Ver puntuaciones");
        System.out.println("4. Salir");
        System.out.println("======================");
    }

    private static void iniciarNuevaPartida() {
        System.out.println("\n--- Creación de personaje ---");

        String nombrePersonaje = LeerScanner.leerString("Nombre del personaje: ");

        String genero = null;
        while (true) {
            genero = LeerScanner.leerString("Género (Hombre / Mujer / Desconocido): ").trim();
            String g = genero.toLowerCase();
            if (g.equals("hombre") || g.equals("mujer") || g.equals("desconocido")) {
                break;
            }
            System.out.println("Género no válido. Solo se permite: Hombre, Mujer o Desconocido.");
        }

        // Característica principal eliminada por completo

        int fuerza = 5;
        int resistencia = 5;
        int velocidad = 5;
        int puntosRestantes = 10;

        System.out.println("\nAtributos base: Fuerza=5, Resistencia=5, Velocidad=5");
        System.out.println("Tienes 10 puntos para distribuir.");

        while (puntosRestantes > 0) {
            System.out.println("\nPuntos restantes: " + puntosRestantes);
            System.out.println("1. Aumentar Fuerza");
            System.out.println("2. Aumentar Resistencia");
            System.out.println("3. Aumentar Velocidad");
            int stat = obtenerOpcionValida("Elige el atributo a mejorar: ", 1, 3);

            int cantidad = obtenerOpcionValida("¿Cuántos puntos gastar (1-" + puntosRestantes + ")? ", 1, puntosRestantes);

            switch (stat) {
                case 1 -> fuerza += cantidad;
                case 2 -> resistencia += cantidad;
                case 3 -> velocidad += cantidad;
            }

            puntosRestantes -= cantidad;
        }

        // Creamos personaje sin característica
        Personaje heroe = new Personaje(nombrePersonaje, genero, fuerza, resistencia, velocidad);

        partidaActual = new Partida(usuarioActual, heroe);
        juegoEnCurso = true;

        System.out.println("\nPartida iniciada. Prepárate para entrar al Limbo...\n");
        heroe.mostrar();

        jugarPartida("limbo_inicial");
    }

    private static void jugarPartida(String claveEscenaActual) {
        try {
            while (juegoEnCurso) {
                Escena escenaActual = escenas.get(claveEscenaActual);
                if (escenaActual == null) {
                    System.out.println("Escena no encontrada. Volviendo al inicial.");
                    claveEscenaActual = "limbo_inicial";
                    continue;
                }

                System.out.println("\n" + escenaActual.getDescripcion());
                System.out.println("Opciones:");
                int index = 1;
                for (Menu m : escenaActual.getMenus()) {
                    System.out.println(index + ". " + m.getTexto());
                    index++;
                }

                int eleccion = obtenerOpcionValida("Tu decisión: ", 1, escenaActual.getMenus().size());

                Menu menuElegido = escenaActual.getMenus().get(eleccion - 1);
                String accionElegida = menuElegido.getTexto();
                String siguienteClave = menuElegido.getClaveSiguiente();

                partidaActual.registrarAcciones(accionElegida);
                partidaActual.incrementarTurno();

                System.out.println("Has elegido: " + accionElegida);

                afectarAtributos(menuElegido, partidaActual.getPersonaje());

                if (claveEscenaActual.equals("escena_grito") && eleccion == 1) {
                    eventoAleatorio(partidaActual.getPersonaje());
                }

                if (siguienteClave.equals("combate")) {
                    boolean victoria = combatir(partidaActual.getPersonaje());
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

            usuarioActual.getPartidas().add(partidaActual);

        } catch (Exception e) {
            System.out.println("Error inesperado durante la partida: " + e.getMessage());
        }
    }

    private static void afectarAtributos(Menu menu, Personaje personaje) {
        String texto = menu.getTexto().toLowerCase();
        if (texto.contains("absorber") || texto.contains("tomar")) {
            Atributos.aumentarResistencia(personaje, 2);
            System.out.println("¡Resistencia aumentada en 2!");
        } else if (texto.contains("explorar") || texto.contains("atacar")) {
            Atributos.reducirFuerza(personaje, 1);
            System.out.println("¡Fuerza reducida en 1 por el esfuerzo!");
        } else if (texto.contains("huir") || texto.contains("correr")) {
            Atributos.aumentarAgilidad(personaje, 1);
            System.out.println("¡Velocidad aumentada en 1 por la huida!");
        }
    }

    private static void eventoAleatorio(Personaje personaje) {
        int chance = random.nextInt(100);
        if (chance < 30) {
            Atributos.aumentarFuerza(personaje, 3);
            System.out.println("¡Evento aleatorio positivo! Encuentras una fuente de poder. Fuerza +3.");
        } else if (chance < 60) {
            Atributos.reducirResistencia(personaje, 2);
            System.out.println("¡Evento aleatorio negativo! Una sombra te debilita. Resistencia -2.");
        } else {
            System.out.println("¡Evento aleatorio neutral! Nada sucede.");
        }
    }

    private static boolean combatir(Personaje jugador) {
        Personaje enemigo = new Personaje("Entidad Oscura", "Desconocido", "Hostil", 7, 6, 4);
        int vidaJugador = jugador.getResistencia() * 5;
        int vidaEnemigo = enemigo.getResistencia() * 5;

        System.out.println("\n¡Combate iniciado contra " + enemigo.getNombre() + "!");
        enemigo.mostrar();

        while (vidaJugador > 0 && vidaEnemigo > 0) {
            System.out.println("\nTu turno: Vida=" + vidaJugador + " | Enemigo Vida=" + vidaEnemigo);
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            int accion = obtenerOpcionValida("Elige acción: ", 1, 2);

            if (accion == 1) {
                int damage = jugador.getFuerza() - (enemigo.getResistencia() / 2);
                if (random.nextInt(100) < enemigo.getVelocidad() * 5) {
                    System.out.println("¡El enemigo esquiva!");
                } else {
                    vidaEnemigo -= Math.max(1, damage);
                    System.out.println("¡Atacas y causas " + damage + " de daño!");
                }
            } else {
                System.out.println("¡Te defiendes! Reduces daño entrante.");
            }

            if (vidaEnemigo <= 0) break;

            int damageEnemigo = enemigo.getFuerza() - (jugador.getResistencia() / 2);
            if (accion == 2) damageEnemigo /= 2;
            if (random.nextInt(100) < jugador.getVelocidad() * 5) {
                System.out.println("¡Esquivas el ataque enemigo!");
            } else {
                vidaJugador -= Math.max(1, damageEnemigo);
                System.out.println("El enemigo ataca y causa " + damageEnemigo + " de daño.");
            }
        }

        if (vidaJugador > 0) {
            System.out.println("\n¡Victoria! Has derrotado al enemigo.");
            return true;
        } else {
            System.out.println("\nDerrota... El enemigo te ha vencido.");
            return false;
        }
    }

    private static void mostrarPartidasAnteriores() {
        List<Partida> partidas = usuarioActual.getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("\nNo hay partidas anteriores.");
            return;
        }

        System.out.println("\nPartidas anteriores (" + partidas.size() + " en total):");
        for (int i = 0; i < partidas.size(); i++) {
            Partida p = partidas.get(i);
            System.out.printf("%d. ID: %d | Turnos: %d | Resultado: %s%n",
                    i + 1, p.getId(), p.getTurnos(), p.getResultado());
        }
    }

    private static void mostrarPuntuaciones() {
        List<Partida> partidas = usuarioActual.getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("\nNo hay puntuaciones registradas.");
            return;
        }

        System.out.println("\nPuntuaciones (" + partidas.size() + " partidas jugadas):");
        int maxPuntos = Integer.MIN_VALUE;
        Partida partidaMax = null;

        for (Partida p : partidas) {
            Puntuacion punt = p.getPuntuacion();
            System.out.printf("Partida ID: %d | Puntos: %d | %s%n",
                    p.getId(), punt.getPuntos(), punt.getComentario());

            if (punt.getPuntos() > maxPuntos) {
                maxPuntos = punt.getPuntos();
                partidaMax = p;
            }
        }

        if (partidaMax != null) {
            System.out.println("\nPuntuación máxima: " + maxPuntos + " en Partida ID: " + partidaMax.getId());
        }
    }

    /**
     * Lee un entero de forma segura con mensaje y reintento automático si falla.
     */
    private static int leerEnteroSeguro(String mensaje) {
        while (true) {
            try {
                return LeerScanner.leerEntero(mensaje);
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un número entero válido.");
                LeerScanner.limpiarScanner();
            }
        }
    }

    private static int obtenerOpcionValida(String mensaje, int min, int max) {
        while (true) {
            try {
                int opcion = leerEnteroSeguro(mensaje);
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.printf("Opción no válida. Debe estar entre %d y %d.%n", min, max);
            } catch (Exception e) {
                System.out.println("Error: Debes introducir un número entero válido.");
                LeerScanner.limpiarScanner();
            }
        }
    }

}
