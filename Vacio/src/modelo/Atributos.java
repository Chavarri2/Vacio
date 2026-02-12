// Atributos.java
/**
 * Clase auxiliar con métodos estáticos para manejar atributos de personajes.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
package modelo;

public class Atributos {
    public static void aumentarFuerza(Personaje p, short cantidad) {
        p.setFuerza((short) (p.getFuerza() + cantidad));
    }

    public static void aumentarResistencia(Personaje p, short cantidad) {
        p.setResistencia((short) (p.getResistencia() + cantidad));
    }

    public static void aumentarAgilidad(Personaje p, short cantidad) {
        p.setVelocidad((short) (p.getVelocidad() + cantidad));
    }

    public static void reducirFuerza(Personaje p, short cantidad) {
        p.setFuerza((short) (p.getFuerza() - cantidad));
    }

    public static void reducirResistencia(Personaje p, short cantidad) {
        p.setResistencia((short) (p.getResistencia() - cantidad));
    }

    public static void reducirAgilidad(Personaje p, short cantidad) {
        p.setVelocidad((short) (p.getVelocidad() - cantidad));
    }

    public static void mostrarAtributos(Personaje p) {
        p.mostrar();
    }
}