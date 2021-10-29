package es.florida.ae3;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Box;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Vista {

	private JFrame frame;
	private JTextField textField_Identificador;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JTextField textField_Titol;
	private JTextField textField_Autor;
	private JTextField textField_AnyPublicacio;
	private JTextField textField_Editorial;
	private JTextField textField_numPagines;

	// Constructor: Vista
	// Descripcio: constructor de la clase vista que inicialitza la part visual
	// Parametres d'entrada: no.
	// Parametres de salida: no.
	public Vista() {
		initialize();
	}

		// Metode: initialize
		// Descripcio: metode que gestiona la creacio de la aplicacio a nivel visual.
		// Parametres d'entrada: no.
		// Parametres de salida: no.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 336);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Identificador");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(323, 42, 110, 42);
		frame.getContentPane().add(lblNewLabel);
		
		textField_Identificador = new JTextField();
		textField_Identificador.setBounds(443, 56, 46, 20);
		frame.getContentPane().add(textField_Identificador);
		textField_Identificador.setColumns(10);
		
		btn1 = new JButton("Mostrar tots els titols de la biblioteca");
		btn1.setBounds(10, 42, 233, 23);
		frame.getContentPane().add(btn1);
		
		btn2 = new JButton("Mostrar informacio detallada d'un llibre");
		btn2.setBounds(10, 76, 233, 23);
		frame.getContentPane().add(btn2);
		
		JLabel lblTitol = new JLabel("Titol");
		lblTitol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitol.setBounds(323, 76, 46, 42);
		frame.getContentPane().add(lblTitol);
		
		textField_Titol = new JTextField();
		textField_Titol.setColumns(10);
		textField_Titol.setBounds(364, 87, 178, 20);
		frame.getContentPane().add(textField_Titol);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAutor.setBounds(323, 116, 46, 42);
		frame.getContentPane().add(lblAutor);
		
		textField_Autor = new JTextField();
		textField_Autor.setColumns(10);
		textField_Autor.setBounds(364, 129, 178, 20);
		frame.getContentPane().add(textField_Autor);
		
		JLabel lblAnypublicacio = new JLabel("AnyPublicacio");
		lblAnypublicacio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnypublicacio.setBounds(323, 160, 85, 42);
		frame.getContentPane().add(lblAnypublicacio);
		
		textField_AnyPublicacio = new JTextField();
		textField_AnyPublicacio.setColumns(10);
		textField_AnyPublicacio.setBounds(418, 173, 46, 20);
		frame.getContentPane().add(textField_AnyPublicacio);
		
		JLabel lblEditorial = new JLabel("Editorial");
		lblEditorial.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEditorial.setBounds(323, 194, 57, 42);
		frame.getContentPane().add(lblEditorial);
		
		textField_Editorial = new JTextField();
		textField_Editorial.setColumns(10);
		textField_Editorial.setBounds(379, 204, 163, 20);
		frame.getContentPane().add(textField_Editorial);
		
		JLabel lblNumpagines = new JLabel("NumPagines");
		lblNumpagines.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumpagines.setBounds(323, 233, 85, 42);
		frame.getContentPane().add(lblNumpagines);
		
		textField_numPagines = new JTextField();
		textField_numPagines.setColumns(10);
		textField_numPagines.setBounds(418, 246, 46, 20);
		frame.getContentPane().add(textField_numPagines);
		
		btn3 = new JButton("Crear nou llibre");
		btn3.setBounds(10, 110, 233, 23);
		frame.getContentPane().add(btn3);
		
		btn4 = new JButton("Actualitzar llibre");
		btn4.setBounds(10, 144, 233, 23);
		frame.getContentPane().add(btn4);
		
		btn5 = new JButton("Borrar llibre");
		btn5.setBounds(10, 179, 233, 23);
		frame.getContentPane().add(btn5);
		
		btn6 = new JButton("Tancar la biblioteca");
		btn6.setBounds(10, 213, 233, 23);
		frame.getContentPane().add(btn6);
		this.frame.setVisible(true);
	}

	// Metode: getTextField_Idenfitifcador
	// Descripcio: metode retorna el objecte TextField_Identificador.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField textField_Identificador
	public JTextField getTextField_Identificador() {
		return textField_Identificador;
	}

	// Metode: getBtn1
	// Descripcio: fa referencia a la 1 opcio del programa en consola.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btn1
	public JButton getBtn1() {
		return btn1;
	}

	// Metode: getBtn2
	// Descripcio: fa referencia a la 2 opcio del programa en consola.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btn2
	public JButton getBtn2() {
		return btn2;
	}

	// Metode: getBtn3
	// Descripcio: fa referencia a la 3 opcio del programa en consola.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btn3
	public JButton getBtn3() {
		return btn3;
	}
	
	// Metode: getBtn4
	// Descripcio: fa referencia a la 4 opcio del programa en consola.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btn4
	public JButton getBtn4() {
		return btn4;
	}

	// Metode: getBtn5
	// Descripcio: fa referencia a la 5 opcio del programa en consola.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btn5
	public JButton getBtn5() {
		return btn5;
	}

	// Metode: getBtn6
	// Descripcio: fa referencia a la 6 opcio del programa en consola.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btn6
	public JButton getBtn6() {
		return btn6;
	}

	// Metode: getTextField_Titol
	// Descripcio: metode retorna el objecte TextField_Titol.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField textField_Titol
	public JTextField getTextField_Titol() {
		return textField_Titol;
	}

	// Metode: getTextField_Autor
	// Descripcio: metode retorna el objecte TextField_Autor.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField textField_Autor
	public JTextField getTextField_Autor() {
		return textField_Autor;
	}

	// Metode: getTextField_AnyPublicacio
	// Descripcio: metode retorna el objecte TextField_AnyPublicacio.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField textField_AnyPublicacio
	public JTextField getTextField_AnyPublicacio() {
		return textField_AnyPublicacio;
	}

	// Metode: getTextField_Editorial
	// Descripcio: metode retorna el objecte textField_Editorial.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField textField_Editorial
	public JTextField getTextField_Editorial() {
		return textField_Editorial;
	}

	// Metode: getTextField_numPagines
	// Descripcio: metode retorna el objecte textField_numPagines.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField textField_numPagines
	public JTextField getTextField_numPagines() {
		return textField_numPagines;
	}
	
	// Metode: mostrarMensaje
	// Descripcio: metode retorna una ventana amb el missatge que li pasem per parametre.
	// Parametres d'entrada: un String que es el missatge
	// Parametres de salida: no.
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}
}
