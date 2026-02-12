package com.vacio.modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.vacio.excepcion.Excepciones;
import com.vacio.utils.LeerScanner;
import com.vacio.utils.Validaciones;

import java.util.ArrayList;
import java.util.List;
import com.vacio.modelo.Juego;

public class Juego {
    private static Usuario usuarioActual = null;
    private static Map<String, Escena> escenas = new HashMap<>();
    private static Partida partidaActual;
    private static boolean juegoEnCurso = false;
    private static List<Usuario> usuarios = new ArrayList<>();

    private static Random random = new Random();

    public Juego() {
    }

    public Usuario getusuarioactual() {
        return usuarioActual;
    }

    public void inicializarEscenas() {

        Escena limboInicial = new Escena("Limbo Inicial",
                "Te encuentras en un vasto vacío gris. Delante ves tres caminos...");
        limboInicial.getMenus().add(new Menu("1. Avanzar hacia la luz tenue.", "escena_luz"));
        limboInicial.getMenus().add(new Menu("2. Retroceder hacia la oscuridad.", "escena_oscuridad"));
        limboInicial.getMenus().add(new Menu("3. Gritar pidiendo ayuda.", "escena_grito"));
        escenas.put("limbo_inicial", limboInicial);

        Escena escenaLuz = new Escena("Camino de Luz",
                "La luz te envuelve, sientes una energía revitalizante... pero ¿es una trampa?");
        escenaLuz.getMenus().add(new Menu("1. Absorber la luz (aumenta resistencia).", "escena_final_bueno"));
        escenaLuz.getMenus().add(new Menu("2. Huir de la luz.", "limbo_inicial"));
        escenas.put("escena_luz", escenaLuz);

        Escena escenaOscuridad = new Escena("Camino de Oscuridad",
                "La oscuridad te consume, sientes debilidad... pero quizás haya un secreto.");
        escenaOscuridad.getMenus()
                .add(new Menu("1. Explorar la oscuridad (riesgo de perder fuerza).", "escena_secreto"));
        escenaOscuridad.getMenus().add(new Menu("2. Volver atrás.", "limbo_inicial"));
        escenas.put("escena_oscuridad", escenaOscuridad);

        Escena escenaGrito = new Escena("Eco del Grito", "Tu grito resuena... algo se acerca.");
        escenaGrito.getMenus().add(new Menu("1. Esperar (evento aleatorio).", "escena_encuentro"));
        escenaGrito.getMenus().add(new Menu("2. Correr.", "limbo_inicial"));
        escenas.put("escena_grito", escenaGrito);

        Escena escenaSecreto = new Escena("Secreto en la Oscuridad",
                "Encuentras un artefacto antiguo que aumenta tu velocidad.");
        escenaSecreto.getMenus().add(new Menu("1. Tomar el artefacto.", "limbo_inicial"));
        escenaSecreto.getMenus().add(new Menu("2. Ignorarlo.", "escena_oscuridad"));
        escenas.put("escena_secreto", escenaSecreto);

        Escena escenaEncuentro = new Escena("Encuentro Misterioso",
                "Una entidad hostil aparece... ¡prepárate para combatir!");
        escenaEncuentro.getMenus().add(new Menu("1. Atacar (usa fuerza).", "combate"));
        escenaEncuentro.getMenus().add(new Menu("2. Defender (usa resistencia).", "combate"));
        escenaEncuentro.getMenus().add(new Menu("3. Huir (usa velocidad).", "limbo_inicial"));
        escenas.put("escena_encuentro", escenaEncuentro);

        Escena finalBueno = new Escena("Final Bueno", "Has escapado del limbo con vida. ¡Victoria!");
        escenas.put("escena_final_bueno", finalBueno);
    }

    public void crearNuevoUsuario() throws Excepciones {
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
            System.out.println(
                    "El nickname puede contener letras, números, guiones, puntos y espacios (3-20 caracteres). Intenta de nuevo.");
        }

        short edad = 0;
        while (edad < 13 || edad > 120) {
            edad = Validaciones.leerEnteroSeguro("Introduce tu edad (13-120 años): ");
        }

        usuarioActual = new Usuario(nombre, nickname, edad);
        usuarios.add(usuarioActual);
        System.out.println("\n¡Bienvenid@, " + nickname + "!");
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

        short fuerza = 5;
        short resistencia = 5;
        short velocidad = 5;
        short puntosRestantes = 10;

        System.out.println("\nAtributos base: Fuerza=5, Resistencia=5, Velocidad=5");
        System.out.println("Tienes 10 puntos para distribuir.");

        while (puntosRestantes > 0) {
            System.out.println("\nPuntos restantes: " + puntosRestantes);
            System.out.println("1. Aumentar Fuerza");
            System.out.println("2. Aumentar Resistencia");
            System.out.println("3. Aumentar Velocidad");
            short stat = Validaciones.obtenerOpcionValida("Elige el atributo a mejorar: ", (short) 1, (short) 3);

            short cantidad = Validaciones.obtenerOpcionValida("¿Cuántos puntos gastar (1-" + puntosRestantes + ")? ",
                    (short) 1,
                    puntosRestantes);

            switch (stat) {
                case 1:
                    fuerza += cantidad;
                case 2:
                    resistencia += cantidad;
                case 3:
                    velocidad += cantidad;
            }

            puntosRestantes -= cantidad;
        }

        // Creamos personaje sin característica
        Personaje heroe = new Personaje(nombrePersonaje, genero, fuerza, resistencia, velocidad);
        heroe.setCaracteristica("Neutral");

        partidaActual = new Partida(usuarioActual, heroe);
        juegoEnCurso = true;

        System.out.println("\nPartida iniciada. Prepárate para entrar al Limbo...\n");
        heroe.mostrar();

        jugarPartida("limbo_inicial");
    }

    public void jugarPartida(String claveEscenaActual) {
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
                short index = 1;
                for (Menu m : escenaActual.getMenus()) {
                    System.out.println(index + ". " + m.getTexto());
                    index++;
                }

                short eleccion = Validaciones.obtenerOpcionValida("Tu decisión: ", (short) 1,
                        (short) escenaActual.getMenus().size());

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

    public void afectarAtributos(Menu menu, Personaje personaje) {
        String texto = menu.getTexto().toLowerCase();
        if (texto.contains("absorber") || texto.contains("tomar")) {
            Atributos.aumentarResistencia(personaje, (short) 2);
            System.out.println("¡Resistencia aumentada en 2!");
        } else if (texto.contains("explorar") || texto.contains("atacar")) {
            Atributos.reducirFuerza(personaje, (short) 1);
            System.out.println("¡Fuerza reducida en 1 por el esfuerzo!");
        } else if (texto.contains("huir") || texto.contains("correr")) {
            Atributos.aumentarAgilidad(personaje, (short) 1);
            System.out.println("¡Velocidad aumentada en 1 por la huida!");
        }
    }

    public void eventoAleatorio(Personaje personaje) {
        short chance = (short) random.nextInt(100);
        if (chance < 30) {
            Atributos.aumentarFuerza(personaje, (short) 3);
            System.out.println("¡Evento aleatorio positivo! Encuentras una fuente de poder. Fuerza +3.");
        } else if (chance < 60) {
            Atributos.reducirResistencia(personaje, (short) 2);
            System.out.println("¡Evento aleatorio negativo! Una sombra te debilita. Resistencia -2.");
        } else {
            System.out.println("¡Evento aleatorio neutral! Nada sucede.");
        }
    }

    public boolean combatir(Personaje jugador) {
        Personaje enemigo = new Personaje("Entidad Oscura", "Desconocido", "Hostil", (short) 7, (short) 6, (short) 4);
        short vidaJugador = (short) (jugador.getResistencia() * 5);
        short vidaEnemigo = (short) (enemigo.getResistencia() * 5);

        System.out.println("\n¡Combate iniciado contra " + enemigo.getNombre() + "!");
        enemigo.mostrar();

        while (vidaJugador > 0 && vidaEnemigo > 0) {
            System.out.println("\nTu turno: Vida=" + vidaJugador + " | Enemigo Vida=" + vidaEnemigo);
            System.out.println("1. Atacar");
            System.out.println("2. Defender");
            short accion = Validaciones.obtenerOpcionValida("Elige acción: ", (short) 1, (short) 2);

            if (accion == 1) {
                short damage = (short) (jugador.getFuerza() - (enemigo.getResistencia() / 2));
                if (random.nextInt(100) < enemigo.getVelocidad() * 5) {
                    System.out.println("¡El enemigo esquiva!");
                } else {
                    vidaEnemigo -= Math.max(1, damage);
                    System.out.println("¡Atacas y causas " + damage + " de daño!");
                }
            } else {
                System.out.println("¡Te defiendes! Reduces daño entrante.");
            }

            if (vidaEnemigo <= 0)
                break;

            short damageEnemigo = (short) (enemigo.getFuerza() - (jugador.getResistencia() / 2));
            if (accion == 2)
                damageEnemigo /= 2;
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

        short opcion = Validaciones.obtenerOpcionValida("Selecciona un usuario: ", (short) 1,
                (short) (usuarios.size() + 1));

        if (opcion == usuarios.size() + 1) {
            crearNuevoUsuario();
        } else {
            usuarioActual = usuarios.get(opcion - 1);
            System.out.println("\nUsuario seleccionado: " + usuarioActual.getNickname());
        }
    }
}
