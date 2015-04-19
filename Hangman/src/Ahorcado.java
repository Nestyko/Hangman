import aux_classes.input_output.C;
import aux_classes.input_output.Print;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Ahorcado{
	
	public static ArrayList<Palabra> palabras = new ArrayList<Palabra>();
	private static BufferedReader archivoNivel;
	public static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	public static void main(String[] args){

			byte opc;
			try{
			cargarJugadores();
			}catch(IOException e){
				Print.errorCen("Error al cargar los jugadores");
			}
			do{
			Print.cls();
			opc = menu();
			
			Print.cls();
			switch(opc){
				case 0:{
					opc = 0;
					continue;
					}
				case 2:{
					byte opc3;
					do{
						Print.cls();
						Print.outSln("1.- Registrar nuevo usuario");
						Print.outSln("2.- Jugar con un usuario existente");
						opc3 = C.in_byte("Seleccion: ");
					}while(opc3 != 0);
					
					String alias = C.in_String("Ingrese el alias: ");
					try{
						while(!Jugador.validarJugador(alias)){
							Print.errorCen("Este alias ya esta registrado, por favor elija otro" );
							alias = C.in_String("Ingrese el alias: ");
						}
					}catch(IOException e){
						Print.errorCen("Error verificando el alias");
					}
					
					Jugador nuevo = new Jugador(alias);
					jugadores.add(nuevo);
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
										Print.outSln("Esta segur@ que desea resetear a: " + jugador.getAlias() 
												+ " (y/n)");
										conf = C.in_char("Seleccion: ");
										if((conf == 'y')|| (conf == 'Y')){
												jugador.resetear();
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
					}while(opc2 != 0);
					break;
				}
				case 4:{
					try{
						cargarPalabras(1);
						}catch(IOException e){
							Print.error("Las palabras para el nivel no han podido ser cargadas");
						}
					break;
				}
				
				case 1:{
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
				Print.outSln("1.- Acerca del Programa");
				Print.outSln("2.- Registrar Jugador");
				Print.outSln("3.- Reiniciar Registro de Jugadores");

				Print.endl(2);
				
				Print.endl(1);
				opc = C.in_byte("Seleccione una opcion: [  ]\b\b\b");
		return opc;
							

	}//menu

	public static boolean cargarJugadores() throws IOException{
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

	public static void cargarPalabras(int nivel) throws IOException{
		boolean fileFound = false;
		String ruta = "";
		String nombre = "nivel" + nivel + ".txt";
		while(!fileFound){
			try{
				if(ruta != ""){
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
		
		String line = null;
		try {
			while(((line=archivoNivel.readLine())!=null)){
				Palabra nueva = new Palabra(line);
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
	
	

}