// Atributos.java
/**
 * Clase auxiliar con métodos estáticos para manejar atributos de personajes.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pizco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

public class Atributos {
    public static void aumentarFuerza(Personaje p, int cantidad) {
        p.setFuerza(p.getFuerza() + cantidad);
    }

    public static void aumentarResistencia(Personaje p, int cantidad) {
        p.setResistencia(p.getResistencia() + cantidad);
    }

    public static void aumentarAgilidad(Personaje p, int cantidad) {
        p.setVelocidad(p.getVelocidad() + cantidad);
    }

    public static void reducirFuerza(Personaje p, int cantidad) {
        p.setFuerza(p.getFuerza() - cantidad);
    }

    public static void reducirResistencia(Personaje p, int cantidad) {
        p.setResistencia(p.getResistencia() - cantidad);
    }

    public static void reducirAgilidad(Personaje p, int cantidad) {
        p.setVelocidad(p.getVelocidad() - cantidad);
    }

    public static void mostrarAtributos(Personaje p) {
        p.mostrar();
    }
}