import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import aux_classes.input_output.Print;


public class Jugador {
	
	private String nombre;
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
	public Jugador(String nombre) {
		this.nombre = nombre;
		this.puntaje = 0;
		this.nivel = 1;
		this.vida = 3;
		try{
			archivarJugador();
		}catch(IOException e){
			Print.errorCen("No se pudo archivar el jugador: " + nombre);
		}
		
	}
	
	/**
	 * Writes the outline in the file and then adds a '\n'
	 * @param output is the file to write on
	 * @param outline is the String to add to the file
	 * @throws IOException
	 */
	private void archivarJugador() throws IOException {
		String[] variables = {
			nombre, puntaje.toString(), nivel.toString(), vida.toString()
		};
		for(int i = 0; i < 4; i++){
			try {
				PrintWriter jugadores = new PrintWriter(new FileWriter("Jugadores.txt", true));
				jugadores.println(variables[i]);
				jugadores.close();
			} catch (FileNotFoundException e) {
				System.err.println("Archivo \"" + "Jugadores.txt" + "\" no encontrado");
			}
		}
		
		
		

	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		nombre = nombre;
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
