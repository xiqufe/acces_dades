package es.florida.ae2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Modelo {
	private String dir;
	
	// Constructor: Modelo
	// Descripcio: constructor de la clase modelo
	// Parametres d'entrada: no.
	// Parametres de salida: no.
	Modelo(){
		dir="AE02_T1_2_Streams_Groucho.txt";
		
	}
	
	// Metode: getContingut
	// Descripcio: metode que retorna una llista amb cada linea del fitxer.
	// Parametres d'entrada: no.
	// Parametres de salida: Un List<String> que es la llista amb cada linea del fitxer.
	public List<String> getContingut() {
		
		List<String> llista = new ArrayList<String>();
		try {
			File fitxer = new File(dir);
			FileReader fr = new FileReader(fitxer);
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			
			while (linea != null) {
				llista.add(linea);
				linea = br.readLine();
			}
			br.close();
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.toString(), "INFO", JOptionPane.INFORMATION_MESSAGE);
		}
		return llista;
	}
	
	// Metode: btnBuscar
	// Descripcio: metode que retorna la cantitat de vegades que apareix una paraula que li passem per parametre.
	// Parametres d'entrada: Un String que es la paraula a buscar.
	// Parametres de salida: Un Integer que son les vegades que apareix la paraula.
	public int btnBuscar(String paraula){
		int contador = 0;
		try {
			for(String s:getContingut()) {
				String[] linea = s.split(" ");
				
				for(String l:linea) {
				
					 
					  // Creo patron que quiero encontrar en la palabra
					  Pattern p = Pattern.compile("\\b"+paraula+"\\b");
					  // Defino en un matcher el String con el que quiero encontrar el pattern
					  Matcher m = p.matcher(l);
					  
					  if(m.find()) {
						  contador+=1;
					  }
				}
			}
		} catch (Exception e) {
		
			JOptionPane.showMessageDialog(new JFrame(), e.toString(), "INFO", JOptionPane.INFORMATION_MESSAGE);
		}
		return contador;
	}
	
	// Metode: btnReemplazar
	// Descripcio: metode que donat una paraula la reemplaza per altra.
	// Parametres d'entrada: Un String que es la paraula a reemplazar i un altre que es el reemplaç.
	// Parametres de salida: Una llista de String que conte totes les linees modificades.
	public List<String> btnReemplazar(String paraula1, String paraula2) {
		List<String> llista = new ArrayList<String>();
		try {
			for(String s:getContingut()) {
				String sfinal = s.replaceAll("\\b"+paraula1+"\\b", paraula2);
				llista.add(sfinal);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.toString(), "INFO", JOptionPane.INFORMATION_MESSAGE);
		}
		return llista;
	}
	
	// Metode: fitxerNou
	// Descripcio: metode que donat una llista et crea un fitxer amb el contingut de la llista.
	// Parametres d'entrada: Una llista de Strings.
	// Parametres de salida: no.
	public void fitxerNou(List<String> llista) {
		File fitxernou = new File("AE02_T1_2_Streams_Groucho_nou.txt");
		
		try {
			FileWriter fr = new FileWriter(fitxernou);
			BufferedWriter bw = new BufferedWriter(fr);
			
			for(String s:llista) {
				bw.write(s+"\n");
			}
			bw.close();
			
			fitxernou.createNewFile();
		}catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), e.toString(), "INFO", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
