package modelo;

import com.fasterxml.jackson.databind.JsonNode;

public final class InstanciaJuego {
    private Usuario usuarioActual = null;
    private JsonNode texto = null;
    
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }
    public JsonNode getTexto() {
        return texto;
    }
    public void setTexto(JsonNode texto) {
        this.texto = texto;
    }
    
}
