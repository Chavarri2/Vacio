package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

/**
 * Clase encargada de la lectura de archivos JSON ubicados dentro del directorio
 * de recursos del proyecto. Utiliza la librería Jackson para convertir el
 * contenido del archivo en un objeto {@link JsonNode}.
 *
 * <p>Esta clase permite cargar archivos JSON desde el classpath utilizando
 * {@code getResourceAsStream}, lo que garantiza compatibilidad tanto en
 * ejecución dentro del IDE como en archivos JAR exportados.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 *     LeerFile lector = new LeerFile();
 *     JsonNode datos = lector.GetJsonNode("/Script.json");
 * </pre>
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026/02/11
 */
public class LeerFile {

    /**
     * Carga un archivo JSON desde la carpeta de recursos y lo convierte en un
     * objeto {@link JsonNode}.
     *
     * @param path Ruta del archivo dentro de resources. Debe comenzar con "/".
     *             Ejemplo: {@code "/Script.json"}.
     *
     * @return Un objeto {@link JsonNode} con el contenido del archivo JSON,
     *         o {@code null} si el archivo no existe o ocurre un error.
     */
    public JsonNode GetJsonNode(String path) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Cargar archivo desde resources
            InputStream is = getClass().getResourceAsStream(path);

            if (is == null) {
                System.out.println("ERROR: No se encontró el archivo JSON.");
                return null;
            }

            return mapper.readTree(is);

        } catch (Exception e) {
            System.out.println("Error en LeerFile");
            return null;
        }
    }
}
