package es.florida.ae4;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Biblioteca {

	private static JFrame frame;
	private static JTextField textField;

	static Connection con;

	// Metode: insert
	// Descripcio: metode per a insertar dades en la base de dades
	// Parametres d'entrada: ArrayList<String>
	// Parametres de salida: no.
	public static void insert(ArrayList<String> linea) {
		try {
			PreparedStatement psInsertar = con.prepareStatement(
					"INSERT INTO Llibres (titol,autor,anyNaixement,anyPublicacio,editorial,pagines) VALUES (?,?,?,?,?,?)");
			psInsertar.setString(1, linea.get(0));
			psInsertar.setString(2, linea.get(1));
			psInsertar.setString(3, linea.get(2));
			psInsertar.setString(4, linea.get(3));
			psInsertar.setString(5, linea.get(4));
			psInsertar.setString(6, linea.get(5));
			int resultadoInsertar = psInsertar.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Metode: consultaSQL
	// Descripcio: metode per a executar cualquier consulta SQL
	// Parametres d'entrada: String
	// Parametres de salida: no.
	public static void consultaSQL(String sql) {
		String tipo = sql.split(" ")[0];
		try {

			switch (tipo) {
			case "SELECT":
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				String opcion = sql.split(" ")[1];
				ArrayList<String> opciones = new ArrayList<String>();
				if (opcion.equals("*")) {
					opciones.add("identificador");
					opciones.add("titol");
					opciones.add("autor");
					opciones.add("anyNaixement");
					opciones.add("anyPublicacio");
					opciones.add("editorial");
					opciones.add("pagines");
				} else {
					Collections.addAll(opciones, opcion.split(","));
				}

				String cadena = "";
				while (rs.next()) {

					for (String s : opciones) {
						cadena += rs.getString(s) + " ";
					}
					cadena+="\n";
				}
				mostrarMensaje(cadena);

				// =========

				break;
				
				default:
					PreparedStatement psSQL = con.prepareStatement(sql);
					int resultado = psSQL.executeUpdate();
					mostrarMensaje("SQL executat correctament");
				break;
			}

		} catch (Exception e) {

		}
	}
	
	// Metode: Main
	// Descripcio: metode principal que llig el arxiu csv i inserta les dades en la base de dades
	// Parametres d'entrada: String[]
	// Parametres de salida: no.
	public static void main(String[] args) {
		// Llegir fitxer
				File csv = new File("AE04_T1_4_JDBC_Dades.csv");
				try {
					// Conexio
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Biblioteca", "root", "");

					FileReader fr = new FileReader(csv);
					BufferedReader br = new BufferedReader(fr);

					String linea = br.readLine();
					linea = br.readLine();
					while (linea != null) {
						ArrayList<String> lineaarr = new ArrayList<String>();
						String[] lineaprocesada = linea.split(";");
						for (String s : lineaprocesada) {
							if (s.equals("")) {
								s = "N.C";
							}
							lineaarr.add(s);
						}
						insert(lineaarr);
						// System.out.println(linea);
						linea = br.readLine();

					}

					//1 consulta por defecto
					//System.out.println("==== Consulta 1 ====");
					
					//consultaSQL("SELECT titol,autor,anyPublicacio FROM Llibres where anyNaixement<1950");
					//2 consulta por defecto
					//System.out.println("==== Consulta 2 ====");
					//consultaSQL("SELECT editorial from llibres where anyPublicacio>2000 group by editorial");
					
					

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		initialize();
	}



	// Metode: initialize
	// Descripcio: inicia la interficie grafica
	// Parametres d'entrada: no.
	// Parametres de salida: no.
	private static void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 561, 344);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Mostrar tots els llibres");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaSQL("SELECT * FROM Llibres");
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(10, 41, 528, 42);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnSeleccionarElTitol = new JButton("Seleccionar el titol, autor i any de publicacio dels llibres de autors naixcuts abans de 1950");
		btnSeleccionarElTitol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaSQL("SELECT titol,autor,anyPublicacio FROM Llibres where anyNaixement<1950");
			}
		});
		btnSeleccionarElTitol.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSeleccionarElTitol.setBounds(10, 111, 528, 42);
		frame.getContentPane().add(btnSeleccionarElTitol);
		
		JButton btnSeleccionarEditorialDels = new JButton("Seleccionar editorials que hagen publicat almenys un llibre en el segle XXI");
		btnSeleccionarEditorialDels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaSQL("SELECT editorial from llibres where anyPublicacio>2000 group by editorial");
			}
		});
		btnSeleccionarEditorialDels.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSeleccionarEditorialDels.setBounds(10, 186, 528, 42);
		frame.getContentPane().add(btnSeleccionarEditorialDels);
		
		textField = new JTextField();
		textField.setBounds(10, 270, 448, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Executar SQL");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 250, 115, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaSQL(textField.getText());
				System.out.println(textField.getText());
			}
		});
		btnOk.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOk.setBounds(469, 257, 51, 42);
		frame.getContentPane().add(btnOk);
		
		frame.setVisible(true);
	}
	// Metode: mostrarMensaje
	// Descripcio: mostra un missatge per pantalla
	// Parametres d'entrada: String
	// Parametres de salida: no.
	public static void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}
}
