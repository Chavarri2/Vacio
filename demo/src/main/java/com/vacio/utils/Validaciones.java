package com.vacio.utils;

import java.util.InputMismatchException;

/**
 * Lee un entero de forma segura con mensaje y reintento automático si falla.
 */
public class Validaciones {
    public static short obtenerOpcionValida(String mensaje, short min, short max) {
        while (true) {
            try {
                short opcion = leerEnteroSeguro(mensaje);
                if (opcion >= min && opcion <= max) {
                    return opcion;
                }
                System.out.printf("Opción no válida. Debe estar entre %d y %d.%n");
            } catch (Exception e) {
                System.out.println("Error: Debes introducir un número entero válido.");
                LeerScanner.limpiarScanner();
            }
        }
    }

    public static short leerEnteroSeguro(String mensaje) {
        while (true) {
            try {
                return LeerScanner.leerEntero(mensaje);
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un número entero válido.");
                LeerScanner.limpiarScanner();
            }
        }
    }

}
