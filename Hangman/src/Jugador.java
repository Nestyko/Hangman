
public class Jugador {
	
	private String Nombre;
	private Integer puntaje;
	private Integer nivel;
	private Integer vida;
	
	
	/**
	 * Constructor por defecto donde se ingresan los nuevos jugadores que van a comenzar a jugar
	 * @param nombre Alias del jugador
	 * @param puntaje 
	 * @param nivel del 1 al 5
	 * @param vida las vidas del Jugador (empieza con 3 vidas)
	 */
	public Jugador(String nombre, Integer puntaje, Integer nivel, Integer vida) {
		Nombre = nombre;
		this.puntaje = 0;
		this.nivel = 1;
		this.vida = 3;
	}


	public String getNombre() {
		return Nombre;
	}


	public void setNombre(String nombre) {
		Nombre = nombre;
	}


	public Integer getPuntaje() {
		return puntaje;
	}


	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}


	public Integer getNivel() {
		return nivel;
	}


	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}


	public Integer getVida() {
		return vida;
	}


	public void setVida(Integer vida) {
		this.vida = vida;
	}
	
	
	

}
