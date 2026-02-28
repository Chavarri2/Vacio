package utils;

import java.util.InputMismatchException;

import excepcion.Excepciones;

/**
 * Lee un entero de forma segura con mensaje y reintento automático si falla.
 */
public class Validaciones {
	public static short obtenerOpcionValida(String mensaje, short min, short max) {
	    while (true) {
	        try {
	            short opcion = leerShortSeguro(mensaje);

	            if (opcion < min || opcion > max) {
	                System.out.println("Opción no valida. El numero debe estar entre " + min + " y " + max);
	            } else {
	                return opcion; // SOLO SALIMOS CUANDO ES VÁLIDO
	            }

	        } catch (Exception e) {
	            System.out.println("Entrada inválida. Debes introducir un número.");
	            LeerScanner.limpiarScanner();
	        }
	    }
	}

    public static short leerShortSeguro(String mensaje) {
        while (true) {
            try {
                return LeerScanner.leerShort(mensaje);
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes introducir un número entero válido.");
                LeerScanner.limpiarScanner();
            }
        }
    }
  //Pide el nombre real del usuario por teclado y lanza una excepcion
    public static String ingresarNombre(String nombre, String mensaje, String pattern, String error) {
    	while (true) {
			try {
				nombre = LeerScanner.leerString(mensaje).trim();
				if (!nombre.matches(pattern)) {
					throw new Excepciones(error);
				}
				break;
			} catch (Excepciones e) {
				System.out.println(e.getMessage());
			}
		}
    	return nombre;
    }

}
