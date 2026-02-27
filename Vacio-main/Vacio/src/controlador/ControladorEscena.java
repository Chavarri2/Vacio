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
		JsonNode escenaInicioHuir = instancia.getTexto().path("EVENTOS").path("CAP1").path("RESPUESTAINICIAL1");
		Escena inicioHuir = new Escena(escenaInicioHuir.path("NOMBRE").asText(), escenaInicioHuir.path("TEXTO").asText());
		// RESPUESTAS escena_empezar_huir
		JsonNode opcionesEscenaHuir = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMIENZO");
		for (JsonNode Item : opcionesEscenaHuir) {
			inicioHuir.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		// GUARDAR escena_empezar_huir
		escenas.put(escenaInicioHuir.path("ID").asText(), inicioHuir);
		
		
		// ESCENA escena_empezar_acercarse
		JsonNode escenaInicioAcercarse = instancia.getTexto().path("EVENTOS").path("CAP1").path("RESPUESTAINICIAL2");
		Escena inicioAcercarse = new Escena(escenaInicioAcercarse.path("NOMBRE").asText(), escenaInicioAcercarse.path("TEXTO").asText());
		// RESPUESTAS escena_empezar_acercarse
		JsonNode seguirCap2 = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("SEGUIRCAPITULO2");
		inicioAcercarse.getMenus().add(new Respuestas(seguirCap2.path("OPCION").asText(), seguirCap2.path("CLAVE").asText()));
		// GUARDAR escena_empezar_acercarse
		escenas.put(escenaInicioAcercarse.path("ID").asText(), inicioAcercarse);
		
		
		// ESCENA escena_empezar_atacar
		JsonNode escenaInicioAtacar = instancia.getTexto().path("EVENTOS").path("CAP1").path("RESPUESTAINICIAL3");
		Escena inicioAtacar = new Escena(escenaInicioAtacar.path("NOMBRE").asText(), escenaInicioAtacar.path("TEXTO").asText());
		// RESPUESTAS escena_empezar_atacar
		JsonNode opcionesContinuar = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESFINAL");
		inicioAtacar.getMenus().add(new Respuestas(opcionesContinuar.path("OPCION").asText(), opcionesContinuar.path("CLAVE").asText()));
		// GUARDAR escena_empezar_atacar
		escenas.put(escenaInicioAtacar.path("ID").asText(), inicioAtacar);
		
		
		//ESCENA final_rioestigio
		JsonNode finalRioestigio = instancia.getTexto().path("EVENTOS").path("FINALES").path("MALO");
		Escena finalMalo = new Escena(finalRioestigio.path("NOMBRE").asText(), finalRioestigio.path("TEXTO").asText());
		// RESPUESTAS final_rioestigio
		JsonNode acabar = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("ACABA");
		finalMalo.getMenus().add(new Respuestas(acabar.path("OPCION").asText(), acabar.path("CLAVE").asText()));
		// GUARDAR escena_empezar_atacar
		escenas.put(finalRioestigio.path("ID").asText(), finalMalo);
		
		
		//ESCENA escena_embarcar
		JsonNode escenaEmbarcar = instancia.getTexto().path("EVENTOS").path("CAP2").path("LLEGADAVACIO");
		Escena embarcar = new Escena(escenaEmbarcar.path("NOMBRE").asText(), escenaEmbarcar.path("TEXTO").asText());
		// RESPUESTAS escena_embarcar
		JsonNode respuestasEmbarcar = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESVACIO");
		embarcar.getMenus().add(new Respuestas(respuestasEmbarcar.path("OPCION").asText(), respuestasEmbarcar.path("CLAVE").asText()));
		// GUARDAR escena_embarcar
		escenas.put(escenaEmbarcar.path("ID").asText(), embarcar);
		
		
		//ESCENA introduce_encuentro
		JsonNode introduceEncuentro = instancia.getTexto().path("EVENTOS").path("CAP2").path("INTRODUCCIONCOMBATE");
		Escena encuentro = new Escena(introduceEncuentro.path("NOMBRE").asText(), introduceEncuentro.path("TEXTO").asText());
		// RESPUESTAS introduce_encuentro
		JsonNode respuestasencuentro = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCAP2");
		for (JsonNode Item : respuestasencuentro) {
		encuentro.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		// GUARDAR introduce_encuentro
		escenas.put(introduceEncuentro.path("ID").asText(), encuentro);
		
		
		//ESCENA primer_encuentro
		JsonNode primerEncuentro = instancia.getTexto().path("EVENTOS").path("CAP2").path("PRIMERCOMBATE");
		Escena encuentro1 = new Escena(primerEncuentro.path("NOMBRE").asText(), primerEncuentro.path("TEXTO").asText());
		// RESPUESTAS introduce_encuentro
		JsonNode respuestasprimerencuentro = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESCOMBATE");
		for (JsonNode Item : respuestasprimerencuentro) {
		encuentro1.getMenus().add(new Respuestas(Item.path("OPCION").asText(), Item.path("CLAVE").asText()));
		}
		// GUARDAR introduce_encuentro
		escenas.put(primerEncuentro.path("ID").asText(), encuentro1);
		
		
		//ESCENA final_huir
		JsonNode finalHuir = instancia.getTexto().path("EVENTOS").path("FINALES").path("MALO2");
		Escena huir = new Escena(finalHuir.path("NOMBRE").asText(), finalHuir.path("TEXTO").asText());
		// RESPUESTAS final_huir
		JsonNode respuestaHuir = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("OPCIONESHUIR");
		huir.getMenus().add(new Respuestas(respuestaHuir.path("OPCION").asText(), respuestaHuir.path("CLAVE").asText()));
		// GUARDAR final_huir
		escenas.put(finalHuir.path("ID").asText(), huir);
		
		
		//ESCENA final_perdido
		JsonNode finalPerdido = instancia.getTexto().path("EVENTOS").path("FINALES").path("MALO1");
		Escena perdido = new Escena(finalPerdido.path("NOMBRE").asText(), finalPerdido.path("TEXTO").asText());
		// RESPUESTAS final_perdido
		JsonNode respuestaPerdido = instancia.getTexto().path("EVENTOS").path("OPCIONES").path("ACABA");
		perdido.getMenus().add(new Respuestas(respuestaPerdido.path("OPCION").asText(), respuestaPerdido.path("CLAVE").asText()));
		// GUARDAR final_perdido
		escenas.put(finalPerdido.path("ID").asText(), perdido);
	}
}
