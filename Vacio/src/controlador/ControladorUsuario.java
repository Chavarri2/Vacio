package controlador;

import java.util.List;

import excepcion.Excepciones;
import modelo.InstanciaJuego;
import modelo.Usuario;
import utils.Validaciones;

public class ControladorUsuario {
	public static List<Usuario> crearNuevoUsuario(InstanciaJuego instancia,List<Usuario> usuarios) throws Excepciones {
		System.out.println("\nBienvenid@ al Limbo...");

		String nombre = null;
		String nickname = null;
		short edad = 0;

		nombre= Validaciones.ingresarNombre(nombre, "Ingresa tu nombre real", "^[A-Za-zÁÉÍÓÚÜáéíóúüÑñ ]{2,50}$","Tu nombre solo puede contener letras de la A a la Z y debe tener de 2-50 caracteres");
		nickname= Validaciones.ingresarNombre(nickname, "Ingresa tu nickname", "^[\\w\\-\\. ]{3,20}$", "El nickname puede contener letras, números, guiones, puntos y espacios (3-20 caracteres). Intenta de nuevo.");
		edad=Validaciones.obtenerOpcionValida("Introduce tu edad (13-120 años): ",(short) 13, (short) 120);
		
		instancia.setUsuarioActual(new Usuario(nombre, nickname, edad));
		usuarios.add(instancia.getUsuarioActual());
		System.out.println("\n¡Bienvenid@, " + nickname + "!");
		return usuarios;
	}
	public static void seleccionarOcrearUsuario(InstanciaJuego instancia, List<Usuario> usuarios) throws Excepciones {
        System.out.println("\n=== Usuarios disponibles ===");
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios creados aún.");
        } else {
            for (short i = 0; i < usuarios.size(); i++) {
                Usuario u = usuarios.get(i);
                System.out.printf("%d. %s (%s)%n", i + 1, u.getNickname(), u.getNombre());
            }
        }

        System.out.println((usuarios.size() + 1) + ". Crear nuevo usuario");
        System.out.println("============================");

        short opcion;

        do {
            opcion = Validaciones.obtenerOpcionValida(
                "Selecciona una opcion: ",
                (short) 1,
                (short) (usuarios.size() + 1)
            );
        } while (opcion < 1 || opcion > usuarios.size() + 1);

        if (opcion == usuarios.size() + 1) {
            usuarios = ControladorUsuario.crearNuevoUsuario(instancia, usuarios);
        } else {
            instancia.setUsuarioActual(usuarios.get(opcion - 1));
            System.out.println("\nUsuario seleccionado: " + instancia.getUsuarioActual().getNickname());
        }
    }
	/*public static void TestCrearUsuario(InstanciaJuego instancia,List<Usuario> usuarios){
        instancia.setUsuarioActual(new Usuario("Pepe", "Admin", (short) 30));
        usuarios.add(instancia.getUsuarioActual());
    }*/

}
