import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import aux_classes.input_output.Print;


public class Jugador {
	
	private String alias;
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
		this.alias = nombre.toUpperCase();
		this.puntaje = 0;
		this.nivel = 0;
		this.vida = 3;
		try{
			archivarJugador();
		}catch(IOException e){
			Print.errorCen("No se pudo archivar el jugador: " + nombre);
		}
		
	}
	
	/**
	 * Constructor para crear un jugador temporal que no se guardara en el archivo
	 */
	public Jugador() {
		this.alias = "";
		this.puntaje = 0;
		this.nivel = 0;
		this.vida = 3;
		
	}
	
	/**
	 * Writes the outline in the file and then adds a '\n'
	 * @param output is the file to write on
	 * @param outline is the String to add to the file
	 * @throws IOException
	 */
	private void archivarJugador() throws IOException {
		String[] variables = {
			alias, puntaje.toString(), nivel.toString(), vida.toString()
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
	
	
	/**
	 * Verifica que el alias no este repetido en el archivo Jugadores.txt
	 * @param alias es el nombre del jugador a buscar en el archivo
	 * @return false si esta repetido, retorna true si de lo contrario o si no se encuentra el archivo.
	 * @throws IOException
	 */
	public static boolean validarJugador(String alias) throws IOException {
		BufferedReader listaJugadores;
		File archivoJugadores = new File ("Jugadores.txt");
		if(archivoJugadores.exists()){
			String line = "";
			listaJugadores = new BufferedReader(new FileReader(archivoJugadores));
			while((line = listaJugadores.readLine()) != null){
				if(alias.equalsIgnoreCase(line)){
					listaJugadores.close();
					return false;
				}
			}
			listaJugadores.close();
			return true;
		}
		return true;
		
	}


	public String getAlias() {
		return alias;
	}
	
	public void setAlias(String alias){
		BufferedReader listaJugadores;
		File archivoJugadores = new File ("Jugadores.txt");
		if(archivoJugadores.exists()){
			String line = "";StringBuffer input = new StringBuffer("");
			try{
			listaJugadores = new BufferedReader(new FileReader(archivoJugadores));
			while((line = listaJugadores.readLine()) != null){
				input.append(line + "\n");
			}
			listaJugadores.close();
			}catch(FileNotFoundException e1){
				Print.errorCen("Archivo Jugadores.txt no ha podido ser encontrado");
			}catch(IOException e2){
				Print.errorCen("Error al leer el Alias");
			}
			
			int inicio = (input.indexOf(alias));
			int fin = ((this.alias).length())+inicio;
			input.replace(inicio, fin, puntaje.toString());
			this.alias = alias;
			String input2 = input.toString();
			try{
				FileOutputStream output = new FileOutputStream("Jugadores.txt");
				output.write(input2.getBytes());
				output.close();
			}catch ( IOException e){
				Print.errorCen("Error al modificar Alias");
			}
		
		}
	}


	public Integer getPuntaje() {
		return puntaje;
	}
	
	public void resetear(){
		setPuntaje(0);
		setVida(3);
		setNivel(0);
	}


	public void setPuntaje(Integer puntaje) {
		BufferedReader listaJugadores;
		File archivoJugadores = new File ("Jugadores.txt");
		if(archivoJugadores.exists()){
			String line = "";StringBuffer input = new StringBuffer("");
			try{
			listaJugadores = new BufferedReader(new FileReader(archivoJugadores));
			while((line = listaJugadores.readLine()) != null){
				input.append(line + "\n");
			}
			listaJugadores.close();
			}catch(FileNotFoundException e1){
				Print.errorCen("Archivo Jugadores.txt no ha podido ser encontrado");
			}catch(IOException e2){
				Print.errorCen("Error al leer el puntaje");
			}
			
			int inicio = (input.indexOf(alias)+alias.length())+1;
			int fin = ((this.puntaje+"").length())+inicio;
			input.replace(inicio, fin, puntaje.toString());
			this.puntaje = puntaje;
			String input2 = input.toString();
			try{
				FileOutputStream output = new FileOutputStream("Jugadores.txt");
				output.write(input2.getBytes());
				output.close();
			}catch ( IOException e){
				Print.errorCen("Error al modificar el puntaje");
			}
		
		}
		
	}


	public Integer getNivel() {
		return nivel;
	}


	public void setNivel(Integer nivel) {
		BufferedReader listaJugadores;
		File archivoJugadores = new File ("Jugadores.txt");
		if(archivoJugadores.exists()){
			String line = "";StringBuffer input = new StringBuffer("");
			try{
			listaJugadores = new BufferedReader(new FileReader(archivoJugadores));
			while((line = listaJugadores.readLine()) != null){
				input.append(line + "\n");
			}
			listaJugadores.close();
			}catch(FileNotFoundException e1){
				Print.errorCen("Archivo Jugadores.txt no ha podido ser encontrado");
			}catch(IOException e2){
				Print.errorCen("Error al leer el Nivel");
			}
			
			int inicio = (input.indexOf(alias)+alias.length())+2+(puntaje.toString().length());
			int fin = ((this.nivel.toString()).length())+inicio;
			input.replace(inicio, fin, nivel.toString());
			this.nivel = nivel;
			String input2 = input.toString();
			try{
				FileOutputStream output = new FileOutputStream("Jugadores.txt");
				output.write(input2.getBytes());
				output.close();
			}catch ( IOException e){
				Print.errorCen("Error al modificar el Nivel");
			}
		
		}
	}


	public Integer getVida() {
		return vida;
	}
	
	public void cargarAlias(String alias){
		this.alias = alias;
	}
	
	public void cargarPuntaje(Integer puntaje){
		this.puntaje = puntaje;
	}
	
	public void cargarNivel(Integer nivel){
		this.nivel = nivel;
	}
	
	public void cargarVida(Integer vida){
		this.vida = vida;
	}


	public void setVida(Integer vida) {
		BufferedReader listaJugadores;
		File archivoJugadores = new File ("Jugadores.txt");
		if(archivoJugadores.exists()){
			String line = "";StringBuffer input = new StringBuffer("");
			try{
			listaJugadores = new BufferedReader(new FileReader(archivoJugadores));
			while((line = listaJugadores.readLine()) != null){
				input.append(line + "\n");
			}
			listaJugadores.close();
			}catch(FileNotFoundException e1){
				Print.errorCen("Archivo Jugadores.txt no ha podido ser encontrado");
			}catch(IOException e2){
				Print.errorCen("Error al leer las Vidas");
			}
			
			int inicio = (input.indexOf(alias)+alias.length())+3+(puntaje.toString().length())
					+(nivel.toString().length());
			int fin = ((this.vida.toString()).length())+inicio;
			input.replace(inicio,fin, vida.toString());
			this.vida = vida;
			String input2 = input.toString();
			try{
				FileOutputStream output = new FileOutputStream("Jugadores.txt");
				output.write(input2.getBytes());
				output.close();
			}catch ( IOException e){
				Print.errorCen("Error al modificar la Vida");
			}
		
		}
	}
	
	
	

}
