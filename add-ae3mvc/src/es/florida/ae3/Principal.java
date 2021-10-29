package es.florida.ae3;

public class Principal {

	// Metode: main
	// Descripcio: metode principal.
	// Parametres d'entrada: Array de Strings pasat per parametre.
	// Parametres de salida: no.
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		Vista vista = new Vista();
		Controlador controlador = new Controlador(modelo, vista);

	}

}
