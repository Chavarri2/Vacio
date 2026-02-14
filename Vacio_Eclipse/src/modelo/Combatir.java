package modelo;

import java.util.Random;
import utils.Validaciones;

/**
 * Sistema de combate dinámico y desafiante.
 * IA del enemigo basada en vida, probabilidades reales de esquiva
 * y daño coherente basado en fuerza y defensa.
 *
 * @author Carlos
 * @author Violeta
 * @version 2.0
 * @date 14/02/2026
 */
public class Combatir {

    private static Random random = new Random();

    public static boolean combate(Personaje jugador) {

        // Estadísticas aleatorias del enemigo
        short fuerza = (short) (Math.random() * 10 + 5);
        short resistencia = (short) (Math.random() * 10 + 5);
        short velocidad = (short) (Math.random() * 10 + 5);

        Personaje enemigo = new Personaje("Entidad Oscura", "Desconocido", "Hostil",
                fuerza, resistencia, velocidad);

        short vidaJugador = (short) (jugador.getResistencia() * 3);
        short vidaEnemigo = (short) (enemigo.getResistencia() * 3);

        System.out.println("\n¡Combate iniciado contra " + enemigo.getNombre() + "!");
        enemigo.mostrar();

        while (vidaJugador > 0 && vidaEnemigo > 0) {

            System.out.println("\nTu turno: Vida=" + vidaJugador + " | Enemigo Vida=" + vidaEnemigo);
            System.out.println("1. Atacar");
            System.out.println("2. Defender");

            short accionJugador = Validaciones.obtenerOpcionValida("Elige acción: ", (short) 1, (short) 2);

            boolean enemigoDefiende = false;

            // IA del enemigo
            int probAtacar;
            if (vidaEnemigo < vidaJugador) {
                probAtacar = 70; // Va perdiendo → agresivo
            } else {
                probAtacar = 40; // Va ganando → defensivo
            }

            if (random.nextInt(100) >= probAtacar) {
                enemigoDefiende = true;
                System.out.println("El enemigo adopta postura defensiva.");
            }

            // --- ATAQUE DEL JUGADOR ---
            if (accionJugador == 1) {

                short damage = jugador.getFuerza();

                if (enemigoDefiende) {
                    damage /= 2;
                }

                damage = (short) Math.max(1, damage);

                if (esquiva(enemigo.getVelocidad())) {
                    System.out.println("¡El enemigo esquiva tu ataque!");
                } else {
                    vidaEnemigo -= damage;
                    System.out.println("¡Golpeas al enemigo! Daño causado: " + damage);
                }
            } else {
                System.out.println("Te preparas para defenderte.");
            }

            if (vidaEnemigo <= 0) break;

            // --- ATAQUE DEL ENEMIGO ---
            if (!enemigoDefiende) {

                short damageEnemigo = enemigo.getFuerza();

                if (accionJugador == 2) {
                    damageEnemigo /= 2;
                }

                damageEnemigo = (short) Math.max(1, damageEnemigo);

                if (esquiva(jugador.getVelocidad())) {
                    System.out.println("¡Esquivas el ataque enemigo!");
                } else {
                    vidaJugador -= damageEnemigo;
                    System.out.println("El enemigo te golpea causando " + damageEnemigo + " de daño.");
                }
            } else {
                System.out.println("El enemigo se mantiene a la defensiva.");
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

    /** Probabilidad de esquiva basada en velocidad */
    private static boolean esquiva(short velocidad) {
        int prob = velocidad * 5;
        prob = Math.max(5, Math.min(prob, 60)); // entre 5% y 60%
        return random.nextInt(100) < prob;
    }
}
