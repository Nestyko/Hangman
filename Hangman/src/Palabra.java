
public class Palabra {
	private String palabra;
	private String oculta;
	
	
	
	
	
	/**
	 * Ingresa una palabra y la oculta en otra variable con asteriscos y guiones
	 * @param palabra es la palabra a guardar
	 * @param palabraOculta es misma palabra pero oculta
	 */
	public Palabra(String palabra, String palabraOculta) {
		super();
		this.palabra = palabra;
		ocultarPalabra();
	}



	/**
	 * Oculta la palabra y la guarda en "oculta"
	 */
	private void ocultarPalabra(){
		this.oculta = "";
		for (int i = 0; i < palabra.length(); i++) {
			if(palabra.charAt(i) == ' '){
				this.oculta += " - ";
			}else{
				this.oculta += "*";
			}
		}
		
	}





	public String getPalabra() {
		return palabra;
	}


	public void setPalabra(String palabra) {
		this.palabra = palabra;
		ocultarPalabra();
	}


	public String getOculta() {
		return oculta;
	}
	
}
