package controlador;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import modelo.Escena;
import modelo.InstanciaJuego;
import modelo.Respuestas;

public class ControladorEscena {
	public static void inicializarEscenas(InstanciaJuego instancia, Map<String, Escena> escenas) {
		// ESCENA escena_empezar
		JsonNode escenaInicio = instancia.getTexto().path("EVENTOS").path("CAP1").path("ESCENAINICIAL");
		Escena inicio = new Escena(escenaInicio.path("NOMBRE").asText(), escenaInicio.path("TEXTO").asText());

		// RESPUESTAS escena_empezar
		JsonNode opcionesEscenaInicio = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMIENZO");
		for (JsonNode Item : opcionesEscenaInicio) {
			inicio.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		// GUARDAR escena_empezar
		escenas.put(escenaInicio.path("ID").asText(), inicio);
		
		//ESCENA escena_empezar_huir
		JsonNode escenaInicioHuir = instancia.getTexto().path("EVENTOS").path("CAP1").path("RESPUESTAINCIAL!");
		Escena inicioHuir = new Escena(escenaInicioHuir.path("NOMBRE").asText(), escenaInicioHuir.path("TEXTO").asText());
		
		// RESPUESTAS escena_empezar_huir
		JsonNode opcionesEscenaHuir = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMIENZO");
		for (JsonNode Item : opcionesEscenaHuir) {
			inicioHuir.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
	
		// GUARDAR escena_empezar_huir
		escenas.put(escenaInicioHuir.path("ID").asText(), inicioHuir);
		
		// ESCENA escena_empezar_acercarse
		JsonNode escenaInicioAcercarse = instancia.getTexto().path("EVENTOS").path("CAP1").path("ESCENAINICIAL");
		Escena inicioAcercarse = new Escena(escenaInicioAcercarse.path("NOMBRE").asText(), escenaInicioAcercarse.path("TEXTO").asText());
		// RESPUESTAS escena_empezar_acercarse
		JsonNode opcionesEscenaInicioAcercarse = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMIENZO");
		for (JsonNode Item : opcionesEscenaInicioAcercarse) {
			inicioAcercarse.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		// GUARDAR escena_empezar_acercarse
		escenas.put(escenaInicioAcercarse.path("ID").asText(), inicioAcercarse);
		
		// ESCENA escena_empezar_atacar
		JsonNode escenaInicioAtacar = instancia.getTexto().path("EVENTOS").path("CAP1").path("ESCENAINICIAL");
		Escena inicioAtacar = new Escena(escenaInicioAtacar.path("NOMBRE").asText(), escenaInicioAtacar.path("TEXTO").asText());
		
		// RESPUESTAS escena_empezar_atacar
		
		JsonNode opcionesEscenaInicioAtacar = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMIENZO");
		for (JsonNode Item : opcionesEscenaInicioAtacar) {
			inicioAtacar.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		
		// GUARDAR escena_empezar_atacar
		escenas.put(escenaInicioAtacar.path("ID").asText(), inicioAtacar);
		
	}
}
