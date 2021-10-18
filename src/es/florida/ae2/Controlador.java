package es.florida.ae2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Controlador {
	private Modelo modelo;
	private Vista vista;
	private ActionListener actionListenerBotonBuscar, actionListenerBotonReemplazar;
	
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
		//AreaOriginal
			for(String s:modelo.getContingut()){
				vista.getTextAreaOriginal().append(s+"\n");
			}

		
		//Boton Buscar
		actionListenerBotonBuscar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String paraula = vista.getTextFieldBuscar().getText();
				int contador = modelo.btnBuscar(paraula);
				vista.mostrarMensaje("La paraula "+paraula+ " apareix "+contador+" vegades.");
			}
		};
		vista.getBtnBuscar().addActionListener(actionListenerBotonBuscar);
		//Boton Reemplazar
		actionListenerBotonReemplazar = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				String paraula1 = vista.getTextFieldBuscar().getText();
				String paraula2 = vista.getTextFieldReemplazar().getText();
				List<String> llistamodificada = modelo.btnReemplazar(paraula1, paraula2);
				for(String s:llistamodificada) {
					vista.getTextAreaModificado().append(s+"\n");
				}
				modelo.fitxerNou(llistamodificada);
			}
		};
		vista.getBtnReemplazar().addActionListener(actionListenerBotonReemplazar);
	}
}
