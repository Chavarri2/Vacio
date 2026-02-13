package modelo;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

/**
 * Clase encargada de gestionar la reproducción de música dentro del proyecto.
 * Utiliza la API estándar de Java para reproducir archivos de audio en formato
 * WAV.
 *
 * El archivo de audio utilizado debe encontrarse en la ruta de recursos del
 * proyecto.
 * En este caso, se espera un archivo llamado "in_the_sea.wav".
 *
 * @author Carlos Abraham Chavarri Valera
 * @author Violeta Pisco
 * @version 1.0
 * @since 2026-02-11
 */
public class Musica {

    /** Clip de audio estático para mantener una única instancia de reproducción. */
    private static Clip clip;

    /**
     * Reproduce la música del archivo "in_the_sea.wav".
     * Si el clip no ha sido inicializado, lo carga desde los recursos.
     */
    public static void reproducir() {
        try {
            if (clip == null) {
            	URL recurso = Musica.class.getResource("/in_the_sea.wav");

                if (recurso == null) {
                    System.out.println("ERROR: No se ha podido cargar la musica.");
                    return;
                }

                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(recurso);
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
            }
            clip.setFramePosition(0); // Reinicia al inicio
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Bucle infinito
            clip.start();
        } catch (Exception e) {
            System.out.println("Error al reproducir música: " + e.getMessage());
        }
    }

    /**
     * Pausa la música si se está reproduciendo.
     */
    public static void pausar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            System.out.println("Música pausada.");
        }
    }
}
