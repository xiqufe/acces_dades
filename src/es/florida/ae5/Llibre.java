package es.florida.ae5;

public class Llibre {

	private Integer identificador;
	private String titol;
	private String autor;
	private String anyPublicacio;
	private String editorial;
	private String numPagines;
	
	public Llibre() {
//		super();
	}

	// Constructor: Llibre
	// Descripcio: constructor de la clase llibre per a quan creem l'objecte
	// Parametres d'entrada: String identificador, String titol, String autor,
	// String anyPublicacio, String editorial, String numPagines
	// Parametres de salida: no.
	public Llibre(Integer identificador, String titol, String autor, String anyPublicacio, String editorial,
			String numPagines) {
		this.identificador = identificador;
		this.titol = titol;
		this.autor = autor;
		this.anyPublicacio = anyPublicacio;
		this.editorial = editorial;
		this.numPagines = numPagines;
	}
	
	public Llibre(String titol, String autor, String anyPublicacio, String editorial,
			String numPagines) {
		this.titol = titol;
		this.autor = autor;
		this.anyPublicacio = anyPublicacio;
		this.editorial = editorial;
		this.numPagines = numPagines;
	}

	// Metode: getIdentificador
	// Descripcio: metode retorna la variable identificador.
	// Parametres d'entrada: no.
	// Parametres de salida: String identificador
	public Integer getIdentificador() {
		return identificador;
	}

	// Metode: setIdentificador
	// Descripcio: metode que cambia el valor per el del parametre
	// Parametres d'entrada: String identificador
	// Parametres de salida: no.
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}

	// Metode: getTitol
	// Descripcio: metode retorna la variable titol.
	// Parametres d'entrada: no.
	// Parametres de salida: String titol
	public String getTitol() {
		return titol;
	}

	// Metode: setTitol
	// Descripcio: metode que cambia el valor per el del parametre
	// Parametres d'entrada: String titol
	// Parametres de salida: no.
	public void setTitol(String titol) {
		this.titol = titol;
	}

	// Metode: getAutor
	// Descripcio: metode retorna la variable autor.
	// Parametres d'entrada: no.
	// Parametres de salida: String autor
	public String getAutor() {
		return autor;
	}

	// Metode: setAutor
	// Descripcio: metode que cambia el valor per el del parametre
	// Parametres d'entrada: String autor
	// Parametres de salida: no.
	public void setAutor(String autor) {
		this.autor = autor;
	}

	// Metode: getAnyPublicacio
	// Descripcio: metode retorna la variable anyPublicacio.
	// Parametres d'entrada: no.
	// Parametres de salida: String anyPublicacio
	public String getAnyPublicacio() {
		return anyPublicacio;
	}

	// Metode: setAnyPublicacio
	// Descripcio: metode que cambia el valor per el del parametre
	// Parametres d'entrada: String anyPublicacio
	// Parametres de salida: no.
	public void setAnyPublicacio(String anyPublicacio) {
		this.anyPublicacio = anyPublicacio;
	}

	// Metode: getEditorial
	// Descripcio: metode retorna la variable editorial.
	// Parametres d'entrada: no.
	// Parametres de salida: String editorial
	public String getEditorial() {
		return editorial;
	}

	// Metode: setEditorial
	// Descripcio: metode que cambia el valor per el del parametre
	// Parametres d'entrada: String editorial
	// Parametres de salida: no.
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	// Metode: getNumPagines
	// Descripcio: metode retorna la variable numPagines.
	// Parametres d'entrada: no.
	// Parametres de salida: String numPagines
	public String getNumPagines() {
		return numPagines;
	}

	// Metode: setNumPagines
	// Descripcio: metode que cambia el valor per el del parametre
	// Parametres d'entrada: String numPagines
	// Parametres de salida: no.
	public void setNumPagines(String numPagines) {
		this.numPagines = numPagines;
	}

}
