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
	
	public static void main(String[] args){

			byte opc;
			do{
			Print.cls();
			opc = menu();
			Print.cls();
			switch(opc){
				case 0:{
					opc = 1;
					continue;
					}
				case 1:{
					try{
					cargarPalabras(1);
					}catch(IOException e){
						Print.error("Las palabras para el nivel no han podido ser cargadas");
					}
					
					opc = 0;
					continue;
				}//case 1
				case 2: {
					for (Palabra palabra : palabras) {
						System.out.println(palabra.getPalabra());
					}
				}
				
				case 10:{
					acerca_de();
					Print.pausa("PRESIONE ENTER PARA CONTINUAR");
					opc = 0;
					continue;
					}//case 10
				default:{
					Print.errorCen("Seleccion Invalida");
					opc = 0;
					continue;
				}//default
			}//switch
			
			//C.espacio(10);
			//opc = C.in_byte("Si desea Salir del programa Presione 1: ");

		}while(opc != 1);


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
				Print.outSln("1.- Ingresar un Jugador");

				Print.endl(2);
				Print.outSln("10.- Acerca del Programa");
				Print.endl(1);
				opc = C.in_byte("Seleccione una opcion: [  ]\b\b\b");
		return opc;
							

	}//menu

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