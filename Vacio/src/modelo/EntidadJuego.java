/**
 * 
 */
package modelo;

/**
 * @author Carlos Abraham Chavarri Valera
 * @version 1.0 06/02/2026
 */
public abstract class EntidadJuego {
	/**
	 * Atributos
	 */
	private static int contadorIds=0;
	private int id;
	private String nombre;
	/**
	 * Contructor 1
	 */
	public EntidadJuego() {
		this.id=++contadorIds;
	}
	
	/**
	 * @param nombre
	 */
	public EntidadJuego(String nombre) {
		this.id=++contadorIds;
		this.nombre = nombre;
	}

	/**
	 * @return the contadorIds
	 */
	public static int getContadorIds() {
		return contadorIds;
	}
	/**
	 * @param contadorIds the contadorIds to set
	 */
	public static void setContadorIds(int contadorIds) {
		EntidadJuego.contadorIds = contadorIds;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "EntidadJuego [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
