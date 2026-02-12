package utils;

import java.util.Scanner;

/**
 * Clase auxiliar que centraliza la lectura de datos desde la consola mediante
 * Scanner.
 * Facilita la entrada de datos al usuario mostrando un mensaje previo y
 * gestionando
 * correctamente la limpieza del buffer cuando es necesario.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-01-24
 */
public class LeerScanner {

    /** Instancia estática y única de Scanner para toda la aplicación. */
    private static final Scanner teclado = new Scanner(System.in);

    /**
     * Solicita y lee un número entero (short) desde la consola.
     *
     * @param mensaje El mensaje que se mostrará al usuario antes de leer el valor
     * @return El número entero introducido por el usuario
     */
    public static short leerEntero(String mensaje) {
        System.out.println(mensaje);
        short numero = (short) teclado.nextInt();
        teclado.nextLine(); // Limpiar el salto de línea pendiente
        return numero;
    }

    /**
     * Solicita y lee un número decimal (double) desde la consola.
     *
     * @param mensaje El mensaje que se mostrará al usuario antes de leer el valor
     * @return El número decimal introducido por el usuario
     */
    public static double leerDouble(String mensaje) {
        System.out.println(mensaje);
        double numero = teclado.nextDouble();
        teclado.nextLine(); // Limpiar el salto de línea pendiente
        return numero;
    }

    /**
     * Solicita y lee una cadena de texto (String) desde la consola.
     * Permite introducir cadenas con espacios.
     *
     * @param mensaje El mensaje que se mostrará al usuario antes de leer el valor
     * @return La cadena introducida por el usuario
     */
    public static String leerString(String mensaje) {
        System.out.println(mensaje);
        return teclado.nextLine().trim(); // trim() elimina espacios iniciales y finales
    }

    public static String limpiarScanner() {
        return teclado.nextLine();
    }

    /**
     * Método para cerrar el Scanner cuando ya no se necesite (buena práctica,
     * aunque en aplicaciones de consola pequeñas suele omitirse).
     * <p>
     * Nota: en la mayoría de proyectos de consola simples no es necesario llamarlo.
     */
    public static void cerrar() {
        teclado.close();
    }

}
