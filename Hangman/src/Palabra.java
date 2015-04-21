import java.util.ArrayList;


public class Palabra {
	private String palabra;
	private String oculta;
	
	
	
	
	
	/**
	 * Ingresa una palabra y la oculta en otra variable con asteriscos y guiones
	 * @param palabra es la palabra a guardar
	 * @param palabraOculta es misma palabra pero oculta
	 */
	public Palabra(String palabra) {
		this.palabra = palabra.replace(' ', '-');
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
	
	public int buscarLetra(String letra){
		ArrayList<Integer> coincidencias = buscarCoincidencias(letra);
		StringBuffer aux = new StringBuffer(oculta);
		for(Integer coin: coincidencias){
			aux.replace(coin, coin+1, palabra.charAt(coin)+"");
		}
		oculta = aux.toString();
		return coincidencias.size();
	}
	
	private ArrayList<Integer> buscarCoincidencias(String letra){
			ArrayList<Integer> coincidencias = new ArrayList<Integer>();
			for(int i = 0; i < palabra.length(); i++){
				if(palabra.charAt(i) == letra.charAt(0)){
					coincidencias.add(i);
				}
			}
			return coincidencias;
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
