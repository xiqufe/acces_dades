package es.florida.ae3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador {
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBoton1;
	private ActionListener actionListenerBoton2;
	private ActionListener actionListenerBoton3;
	private ActionListener actionListenerBoton4;
	private ActionListener actionListenerBoton5;
	private ActionListener actionListenerBoton6;

	// Constructor: Controlador
	// Descripcio: constructor de la clase controlador que asigna el modelo i vista i executa el metodo control.
	// Parametres d'entrada: Modelo i Vista.
	// Parametres de salida: no.
	Controlador(Modelo modelo, Vista vista){
		this.modelo = modelo;
		this.vista = vista;
		control();
	}
	
	// Metode: control
	// Descripcio: metode que gestiona la clase controlador. Açi es on es dona accio als botons.
	// Parametres d'entrada: Array de Strings pasat per parametre.
	// Parametres de salida: no.
	private void control() {
		//Boton 1
		actionListenerBoton1 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String cadena = "";
				for(Llibre l:modelo.recuperarTots()) {
					cadena+="=== "+l.getTitol()+" ==="+"\n";
					cadena+="Identificador: "+l.getIdentificador()+"\n";
				}
				vista.mostrarMensaje(cadena);
			}
		};
		vista.getBtn1().addActionListener(actionListenerBoton1);
		//Boton 2
		actionListenerBoton2 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				Integer identificador = Integer.parseInt(vista.getTextField_Identificador().getText());
				Llibre llibre = modelo.recuperarLlibre(identificador);
				
				vista.getTextField_Titol().setText(llibre.getTitol());
				vista.getTextField_Autor().setText(llibre.getAutor());
				vista.getTextField_AnyPublicacio().setText(llibre.getAnyPublicacio());
				vista.getTextField_Editorial().setText(llibre.getEditorial());
				vista.getTextField_numPagines().setText(llibre.getNumPagines());
			}
		};
		vista.getBtn2().addActionListener(actionListenerBoton2);
		//Boton 3
		actionListenerBoton3 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String Identificador = Integer.toString(modelo.recuperarTots().size());
				String Titol = vista.getTextField_Titol().getText();
				String Autor = vista.getTextField_Autor().getText();
				String AnyPublicacio = vista.getTextField_AnyPublicacio().getText();
				String Editorial = vista.getTextField_Editorial().getText();
				String NumPagines = vista.getTextField_numPagines().getText();
				
				Llibre llibrenou = new Llibre(Identificador, Titol, Autor, AnyPublicacio, Editorial, NumPagines);
				modelo.crearLlibre(llibrenou);
				vista.mostrarMensaje("Se ha creat el llibre amb l'identificador "+Identificador);
				
				//Pose en el textField del Identificador el identificador que li correspon al nou llibre
				vista.getTextField_Identificador().setText(Identificador);
			}
		};
		vista.getBtn3().addActionListener(actionListenerBoton3);
		//Boton 4
		actionListenerBoton4 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String Identificador = vista.getTextField_Identificador().getText();
				String Titol = vista.getTextField_Titol().getText();
				String Autor = vista.getTextField_Autor().getText();
				String AnyPublicacio = vista.getTextField_AnyPublicacio().getText();
				String Editorial = vista.getTextField_Editorial().getText();
				String NumPagines = vista.getTextField_numPagines().getText();
				
				modelo.actualitzaLlibre(Identificador, Titol, Autor, AnyPublicacio, Editorial, NumPagines);;
				vista.mostrarMensaje("Se ha modificat el llibre amb l'identificador "+Identificador);
				
			}
		};
		vista.getBtn4().addActionListener(actionListenerBoton4);
		//Boton5
		actionListenerBoton5 = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String Identificador = vista.getTextField_Identificador().getText();
				
				modelo.borrarLlibre(Integer.parseInt(Identificador));
				vista.mostrarMensaje("Se ha eliminat el llibre amb l'identificador "+Identificador);
				
			}
		};
		vista.getBtn5().addActionListener(actionListenerBoton5);
		//Boton6
		actionListenerBoton6= new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
				
			}
		};
		vista.getBtn6().addActionListener(actionListenerBoton6);
	}
	
}
