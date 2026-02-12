package com.vacio.modelo;

import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import com.vacio.utils.Utilidades;

public class Mostrar{

private final InstanciaJuego instancia;
    public Mostrar(InstanciaJuego instancia) {
        this.instancia=instancia;
    }

    public void mostrarPuntuaciones() {
        List<Partida> partidas = instancia.getUsuarioActual().getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("\nNo hay puntuaciones registradas.");
            return;
        }

        System.out.println("\nPuntuaciones (" + partidas.size() + " partidas jugadas):");
        short maxPuntos = (short) Integer.MIN_VALUE;
        Partida partidaMax = null;

        for (Partida p : partidas) {
            Puntuacion punt = p.getPuntuacion();
            System.out.printf("Partida ID: %d | Puntos: %d | %s%n",
                    p.getId(), punt.getPuntos(), punt.getComentario());

            if (punt.getPuntos() > maxPuntos) {
                maxPuntos = punt.getPuntos();
                partidaMax = p;
            }
        }

        if (partidaMax != null) {
            System.out.println("\nPuntuación máxima: " + maxPuntos + " en Partida ID: " + partidaMax.getId());
        }
    }

    public void mostrarPartidasAnteriores() {
        List<Partida> partidas = instancia.getUsuarioActual().getPartidas();
        if (partidas.isEmpty()) {
            System.out.println("\nNo hay partidas anteriores.");
            return;
        }

        System.out.println("\nPartidas anteriores (" + partidas.size() + " en total):");
        for (short i = 0; i < partidas.size(); i++) {
            Partida p = partidas.get(i);
            System.out.printf("%d. ID: %d | Turnos: %d | Resultado: %s%n",
                    i + 1, p.getId(), p.getTurnos(), p.getResultado());
        }
    }

    public void mostrarMenuPrincipal() {
        List<String> menu = Utilidades.jsonToList( instancia.getTexto().path("MENUS").path("MENU"));
        for(String texto: menu){
            System.out.println(texto);
        }
    }
    public void mostrarTituloPrincipal() {
        List<String> menu = Utilidades.jsonToList( instancia.getTexto().path("MENUS").path("INICIO"));
        for(String texto: menu){
            System.out.println(texto);
        }
    }
}
