package es.florida.ae2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class Vista {

	private JFrame frame;
	private JTextField textField_Buscar;
	private JTextField textField_Reemplazar;
	private JButton btnBuscar;
	private JButton btnReemplazar;
	private JTextArea textArea_Original;
	private JTextArea textArea_Modificado;


	// Constructor: Modelo
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
		frame.setBounds(100, 100, 799, 581);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_Original = new JScrollPane();
		scrollPane_Original.setBounds(22, 20, 743, 222);
		frame.getContentPane().add(scrollPane_Original);
		
		textArea_Original = new JTextArea();
		textArea_Original.setLineWrap(true);
		textArea_Original.setRows(12);
		scrollPane_Original.setViewportView(textArea_Original);
		scrollPane_Original.getViewport().setView(textArea_Original);
		
		textField_Buscar = new JTextField();
		textField_Buscar.setBounds(32, 252, 177, 27);
		frame.getContentPane().add(textField_Buscar);
		textField_Buscar.setColumns(10);
		
		textField_Reemplazar = new JTextField();
		textField_Reemplazar.setColumns(10);
		textField_Reemplazar.setBounds(421, 252, 177, 27);
		frame.getContentPane().add(textField_Reemplazar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBuscar.setBounds(219, 252, 120, 27);
		frame.getContentPane().add(btnBuscar);
		
		btnReemplazar = new JButton("Reemplazar");
		btnReemplazar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnReemplazar.setBounds(608, 252, 120, 27);
		frame.getContentPane().add(btnReemplazar);
		
		JScrollPane scrollPane_Modificado = new JScrollPane();
		scrollPane_Modificado.setBounds(22, 300, 743, 222);
		frame.getContentPane().add(scrollPane_Modificado);
		
		textArea_Modificado = new JTextArea();
		textArea_Modificado.setLineWrap(true);
		textArea_Modificado.setRows(12);
		scrollPane_Modificado.setColumnHeaderView(textArea_Modificado);
		scrollPane_Modificado.getViewport().setView(textArea_Modificado);
		
		this.frame.setVisible(true);
	}
	
	// Metode: getBtnBuscar
	// Descripcio: metode retorna el objecte btnBuscar.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton btnBuscar
	public JButton getBtnBuscar() {
		return btnBuscar;
	}
	
	// Metode: getBtnReemplazar
	// Descripcio: metode retorna el objecte BtnReemplazar.
	// Parametres d'entrada: no.
	// Parametres de salida: JButton BtnReemplazar
	public JButton getBtnReemplazar() {
		return btnReemplazar;
	}
	
	// Metode: getTextFieldBuscar
	// Descripcio: metode retorna el objecte getTextFieldBuscar.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField getTextFieldBuscar
	public JTextField getTextFieldBuscar() {
		return textField_Buscar;
	}
	
	// Metode: getTextFieldReemplazar
	// Descripcio: metode retorna el objecte getTextFieldReemplazar.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextField getTextFieldReemplazar
	public JTextField getTextFieldReemplazar() {
		return textField_Reemplazar;
	}
	
	// Metode: getTextAreaOriginal
	// Descripcio: metode retorna el objecte getTextAreaOriginal.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextArea getTextAreaOriginal
	public JTextArea getTextAreaOriginal() {
		return textArea_Original;
	}
	
	// Metode: getTextAreaModificado
	// Descripcio: metode retorna el objecte getTextAreaModificado.
	// Parametres d'entrada: no.
	// Parametres de salida: JTextArea getTextAreaModificado
	public JTextArea getTextAreaModificado() {
		return textArea_Modificado;
	}
	
	// Metode: mostrarMensaje
	// Descripcio: metode retorna una ventana amb el missatge que li pasem per parametre.
	// Parametres d'entrada: un String que es el missatge
	// Parametres de salida: no.
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}

}
