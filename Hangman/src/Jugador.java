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
		this.nivel = 1;
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
		this.nivel = 1;
		this.vida = 3;
		
	}
	
	public void info(){
		Print.separador();
		Print.outCenln(alias);
		Print.separador();
		Print.outSln("Puntaje: " + puntaje);
		Print.outSln("Nivel: " + nivel);
		Print.outSln("Vidas: " + vida);
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
		setNivel(1);
	}
	
	public static void limpiarArchivo(File archi){
		BufferedReader lista;
		if(archi.exists()){
			String line = ""; StringBuffer input =  new StringBuffer("");
			try{
				lista = new BufferedReader(new FileReader(archi));
				
				while((line = lista.readLine())!= null){
					input.append(line.trim() + "\n");
				}
				lista.close();
			}catch(Exception e){
				Print.errorCen("Error al leer el archivo");
			}
			if(input.length() > 1){
			try{
				while(input.charAt(0) == '\n'){
				input.delete(0, 1);
				}
				for(int i = 0; i < (input.length()-2) ; i++){
				if((input.charAt(i) == '\n') && (input.charAt(i+1) == '\n')){
					if(input.charAt(i+2) == '\n'){
						input.delete(i+1, i+2);
					}else{
						input.delete(i, i+1);
					}
					
				}
			}
			
			
			}catch(Exception e){
				
			}
			}
			
			String input2 = input.toString();
			try{
				FileOutputStream output = new FileOutputStream(archi);
				output.write(input2.getBytes());
				output.close();
			}catch ( IOException e){
				Print.errorCen("Error al escribir en archivo");
			}
		}
	}
	
	public void eliminar() {
		limpiarArchivo(new File("Jugadores.txt"));
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
			
			int inicio = (input.indexOf(alias)-1);
			if(inicio < 0){
				inicio = 0;
			}
			int fin = ((this.alias).length())+inicio+(this.puntaje.toString().length()+1)
					+ (this.nivel.toString().length()+1) + (this.vida.toString().length()+1);
			input.replace(inicio, fin, "");
			String input2 = input.toString();
			try{
				FileOutputStream output = new FileOutputStream("Jugadores.txt");
				output.write(input2.getBytes());
				output.close();
			}catch ( IOException e){
				Print.errorCen("Error al eliminar la jugador");
			}
			limpiarArchivo(new File("Jugadores.txt"));
		
		}
	}


	public void setPuntaje(Integer puntaje) {
		if(puntaje < 0){
			puntaje = 0;
		}
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
	
	public void addPuntaje(Integer puntaje) {
		puntaje = this.puntaje+puntaje;
		if(puntaje < 0){
			puntaje = 0;
		}
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
	
	public void addNivel() {
		if(this.nivel < 5){
			this.nivel++;
		}
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
	
	public void addVida(Integer vida) {
		vida = this.vida+vida;
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
