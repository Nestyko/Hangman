import aux_classes.input_output.C;
import aux_classes.input_output.Print;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;


public class Ahorcado{
	
	public static ArrayList<Palabra> palabras = new ArrayList<Palabra>();
	private static BufferedReader archivoNivel;
	public static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	public static Random rand = new Random();
	
	
	public static void main(String[] args){

			byte opc;
			
			do{
				try{
			cargarJugadores();
			}catch(IOException e){
				Print.errorCen("Error al cargar los jugadores");
			}
			Print.cls();
			opc = menu();
			
			Print.cls();
			switch(opc){
				case 0:{
					opc = 0;
					continue;
					}
				case 1:{
					String alias = C.in_String("Ingrese el alias: ");
					alias = alias.toUpperCase();
						while(verificarJugador(alias)){
							Print.errorCen("Este alias ya esta registrado, por favor elija otro" );
							alias = C.in_String("Ingrese el alias: ");
						}

					
					Jugador nuevo = new Jugador(alias);
					jugadores.add(nuevo);
					break;
				}
				case 4:{
					
								String alias = C.in_String("Ingrese el alias: ");
								alias = alias.toUpperCase();
								int index = -1;
								for(int i = 0; i< jugadores.size(); i++){
									if( jugadores.get(i).getAlias().equals(alias)){
										index = i;
									}
								}
								if(index != -1){
									jugar(jugadores.get(index));
								}else{
									Print.errorCen("Este Jugador no esta registrado" );
								}
								break;
								
				}
				case 3: {
					byte opc2 = 0;
					do{
						Print.outSln("0.- Volver");
						Print.outSln("1.- Resetear el registro de TODOS los Jugadores");
						Print.outSln("2.- Resetear el registro de un jugador en especifico");
						opc2 = C.in_byte("Seleccion: ");
						switch(opc2){
							case 0:{
								break;
							}
							case 1:{
								char conf;
								Print.outSln("Esta segur@ que desea resetear a TODOS los jugadores y/n ");
								conf = C.in_char("Seleccion: ");
								if((conf == 'y')|| (conf == 'Y')){
									for(Jugador jugador : jugadores){
										jugador.resetear();
										Print.pausa("Todos los jugadores han sido reseteados");
									}
								}
								break;
							}
							case 2:{
								String alias = C.in_String("Escriba el alias del Jugador: ");
								alias = alias.toUpperCase();
								boolean jugadorEncontrado = false;
								for(Jugador jugador : jugadores){
									if(jugador.getAlias().equals(alias)){
										jugadorEncontrado = true;
										char conf;
										jugador.info();
										Print.endl(1);
										Print.outSln("Esta segur@ que desea resetear a: " + jugador.getAlias() 
												+ " (y/n)");
										conf = C.in_char("Seleccion: ");
										if((conf == 'y')|| (conf == 'Y')){
												jugador.resetear();
												Print.pausa("El jugador \"" + jugador.getAlias() + "\" ha sido reseteado"); 
												opc2 = 0;
												break;
										}else{
											break;
										}
									}
								}
								if(jugadorEncontrado == false){
									Print.errorCen("Jugador \"" + alias + "\" no encontrado");
								}
								
								break;
							}
							default:{
								Print.errorCen("Seleccion Invalida.");
								break;
							}
						}
						break;
					}while(opc2 != 0);
					break;
				}
				case 2:{
					
						for(int j = 0; j < jugadores.size()-1;j++){
							for(int i = 0; i < jugadores.size()-1; i++){
								if(jugadores.get(i).getPuntaje() < jugadores.get(i+1).getPuntaje()){
									Jugador aux = jugadores.get(i);
									jugadores.set(i, jugadores.get(i+1));
									jugadores.set(i+1, aux);
								}
						}
					}
					for(Jugador player : jugadores){
						
						player.info();
						Print.endl(1);
					}
					Print.pausa("PRESIONE ENTER PARA CONTINUAR");
					break;
				}
				
				case 9:{
					acerca_de();
					Print.pausa("PRESIONE ENTER PARA CONTINUAR");
					break;
					}//case 10
				default:{
					Print.errorCen("Seleccion Invalida");
					break;
				}//default
			}//switch
			
			//C.espacio(10);
			//opc = C.in_byte("Si desea Salir del programa Presione 1: ");

		}while(opc != 0);


	}//main


public static byte menu(){

   byte opc;
			Print.separador();
				Print.outCenln("EL AHORCADO");
				Print.endl(1);
				Print.separador();
				Print.espacio(40);
				Print.outln("Numero de Jugadores registrados: ");// + variable que cuenta el numero de vendedores
				
				Print.endl(1);
				
				Print.outSln("0.- Salir del Programa");
				Print.outSln("1.- Registrar un nuevo Jugador");
				Print.outSln("2.- Mostrar Jugadores");
				Print.outSln("3.- Reiniciar Registro de Jugadores");
				Print.outSln("4.- Jugar");
				Print.endl(2);
				Print.outSln("9.- Acerca del Programa");
				Print.endl(1);
				opc = C.in_byte("Seleccione una opcion: [  ]\b\b\b");
		return opc;
							

	}//menu

	public static boolean cargarJugadores() throws IOException{
		jugadores.clear();
		BufferedReader listaJugadores;
		File archivoJugadores = new File ("Jugadores.txt");
		if(archivoJugadores.exists()){
			String line = "";
			listaJugadores = new BufferedReader(new FileReader(archivoJugadores));
			byte cont = 0;int index = 0;
			while((line = listaJugadores.readLine()) != null) {
				switch(cont){
					case 0: {
						jugadores.add(new Jugador());
						jugadores.get(index).cargarAlias(line);
						cont++;
						break;
					}
					case 1:{
						jugadores.get(index).cargarPuntaje(Integer.parseInt(line));
						cont++;
						break;
					}
					case 2:{
						jugadores.get(index).cargarNivel(Integer.parseInt(line));
						cont++;
						break;
					}
					case 3:{
						jugadores.get(index).cargarVida(Integer.parseInt(line));
						cont = 0;
						index++;
						break;
					}
					
				}
			}
			listaJugadores.close();
			return true;
		}
		return true;
	}
	
	/**
	 * Verifica que el alias ya se encuentre registrado
	 * @param alias
	 * @return true si se encuentra el jugador registrado
	 */
	public static boolean verificarJugador(String alias){
		for(Jugador player : jugadores){
			if(player.getAlias().equals(alias)){
				return true;
			}
		}
		return false;
	}

	public static void cargarPalabras(int nivel) throws IOException{
		boolean fileFound = false;
		String ruta = "";
		String nombre = "nivel" + nivel + ".txt";
		while(!fileFound){
			try{
				if(ruta == ""){
					archivoNivel = new BufferedReader(
							new FileReader(new File (nombre))); 
					fileFound = true;
				}
				else{
					archivoNivel = new BufferedReader(
							new FileReader(new File (ruta + nombre))); 
					fileFound = true;
				}
			}catch(FileNotFoundException e){
				ruta = C.in_String("Archivo no encontrado por favor ingrese la ruta de la carpeta donde "
						+ "se encuentran los archivos txt con los niveles"
						+ "\n          Ruta: ");
				fileFound = false;
			}
		}
		
		palabras.clear();
		
		String line = null;
		try {
			while(((line=archivoNivel.readLine())!=null)){
				Palabra nueva = new Palabra(line.toUpperCase());
				palabras.add(nueva);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	public static final void acerca_de(){
	for(int i = 0;i<15;i++){
		System.out.println("\n\n");
		}
	System.out.print("          ");
	System.out.println("Taller de Programacion 2. Juego El Ahorcado");
	System.out.println("\n\n");
	System.out.print("          ");
	System.out.println("Programa realizado por: Nestor Luis Tobon Arrieta");
	System.out.print("          ");
	System.out.println("Cedula de Identidad: 23.863.118");
	System.out.print("          ");
	System.out.println("Seccion N-511");
	System.out.println("\n\n");
	System.out.print("          ");
	System.out.println("Repositorio del programa disponible en:\nURL: https://github.com/Nestyko/Hangman");
	System.out.print("\n");
	
	}//acerca_de
	
	public static void jugar(Jugador player){
		
		do{
			ArrayList<Integer> palabrasFalladas = new ArrayList<Integer>();
			Integer aleatorio = 0;
		try{
			cargarPalabras(player.getNivel());
			}catch(IOException e){
				Print.errorCen("Error al cargar las palabras del nivel " + player.getNivel());
			}
			if(palabras.size() <= palabrasFalladas.size()){
				palabrasFalladas.clear();
			}
				do{
				aleatorio = rand.nextInt(palabras.size());
			}while((palabrasFalladas.contains(aleatorio)) && (palabrasFalladas.size() > 0));
				palabrasFalladas.add(aleatorio);
			Palabra palabra = palabras.get(aleatorio);
			int oportunidades = 5;
			String letra = "";
			String letrasFalladas = "";
			String letrasUsadas = "";
		
		
		while(oportunidades > 0){
			Print.cls();
			Print.outCen("| Nivel " + player.getNivel() + " |");
		Print.endl(1);
		String[] datosJugador = {
			player.getAlias(),
			"Puntaje: " + player.getPuntaje(),
			"Vidas: " + player.getVida()
		};
		Print.imprimir_fila(datosJugador);
		Print.endl(1);
		Print.separador();
		Print.outCen(palabra.getOculta());
		Print.endl(1);
		Print.separador();
		Print.endl(4);
		if(letrasFalladas.length() > 0){
			Print.outS("Letras utilizadas: ");
			for(int i = 0; i < letrasFalladas.length();i++){
				System.out.print(letrasFalladas.charAt(i) + " ");
			}
			Print.endl(1);
		}
		Print.outS("Oportunidades: " + oportunidades);
		letra = C.solo_una_letra(C.in_String("Escriba una letra: "));
		letra = letra.toUpperCase();
		if(letrasUsadas.contains(letra)){
			continue;
		}
		letrasUsadas += letra;
		int coincidencias = palabra.buscarLetra(letra);
		if(player.getNivel() < 5){
			if(coincidencias == 0){
				player.addPuntaje(-4);
				letrasFalladas += letra;
				oportunidades--;
			}else if(coincidencias  == 1){
				player.addPuntaje(3);
			}else if(coincidencias == 2){
				player.addPuntaje(7);
			}else if(coincidencias > 2){
				player.addPuntaje(9);
			}
		}else{
			if(coincidencias == 0){
				player.addPuntaje(-4);
				letrasFalladas += letra;
				oportunidades--;
			}else if(coincidencias  == 1){
				player.addPuntaje(7);
			}else if(coincidencias == 2){
				player.addPuntaje(9);
			}else if(coincidencias > 2){
				player.addPuntaje(11);
			}
		}
		if(palabra.getOculta().equals(palabra.getPalabra())){
			oportunidades = 5;
			palabrasFalladas.clear();
			
			player.setVida(player.getVida()+1);
			if(player.getNivel() == 5){
				player.addPuntaje(15);
				player.setNivel(1);
				player.addVida(2);
				Print.cls();
				Print.outCen("HA FINALIZADO CON VIDA SU MISION");
				Print.endl(5);
				Print.pausa();
			}else{
				player.addPuntaje(10);
				Print.cls();
				Print.endl(5);
				Print.outCen("**********************  " + palabra.getPalabra() + "  **********************");
				Print.endl(2);
				Print.outCen("Felicitaciones deseas avanzar al nivel " + (player.getNivel()+1) + "? y/n");
				char s = C.in_char("");
				if((s == 'y') || (s == 'Y')){
					player.addNivel();
					Print.cls();
					Print.outCen("Felicitaciones has avanzado al nivel " + player.getNivel());
				}else{
					Print.cls();
					Print.outCen("Continuas en el nivel " + player.getNivel());
				}
				Print.pausa();
			}
			break;
		}
		
		}//while
		if(oportunidades == 0){
			player.setVida(player.getVida()-1);
			Print.cls();
			if(player.getVida() == 0){
				Print.outCen("LO SENTIMOS PERO LAMENTABLEMENTE FUE AHORCADO");
				player.eliminar();
				Print.endl(5);
			}else{
				Print.outCenln("Perdiste una Vida :( ");
			Print.outCenln("Solo te quedan " + player.getVida() + " vidas");
			Print.endl(5);
			}
			
			Print.pausa();
		}
		
		}while((player.getVida() > 0) && ( player.getNivel() < 5));
		
	}
	
	
	

}