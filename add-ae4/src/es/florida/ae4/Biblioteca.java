package es.florida.ae4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

public class Biblioteca {

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

				while (rs.next()) {
					String cadena = "";

					for (String s : opciones) {
						cadena += rs.getString(s) + " ";
					}
					System.out.println(cadena);
				}


				break;
				
				default:
					PreparedStatement psSQL = con.prepareStatement(sql);
					int resultado = psSQL.executeUpdate();
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
			System.out.println("==== Consulta 1 ====");
			consultaSQL("SELECT titol,autor,anyPublicacio FROM Llibres where anyNaixement<1950");
			//2 consulta por defecto
			System.out.println("==== Consulta 2 ====");
			consultaSQL("SELECT editorial from llibres where anyPublicacio>2000 group by editorial");
			
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
