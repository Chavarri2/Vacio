/**
 * Proyecto: Vacio - RPG por turnos ambientado en el limbo
 * Excepción lanzada cuando el nombre introducido no es válido.
 *
 * @author Carlos Abraham, Violeta Pizco
 * @version 1.0
 * @since 11/02/2026
 */
package excepcion;

public class NombreInvalidoExcepcion extends Exception {

    public NombreInvalidoExcepcion(String mensaje) {
        super(mensaje);
    }
}