// AccionInvalidaExcepcion.java
/**
 * Excepción personalizada para acciones inválidas.
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pizco
 * @version 1.0
 * @since 2026-02-11
 */
package excepcion;

public class AccionInvalidaExcepcion extends Exception {
    public AccionInvalidaExcepcion(String mensaje) {
        super(mensaje);
    }
}