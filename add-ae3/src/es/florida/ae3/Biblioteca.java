package es.florida.ae3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;

public class Biblioteca {

	// Metode: writeXML
	// Descripcio: escriu el fitxer XML a traves de la llista proporcionada per
	// parametre
	// Parametres d'entrada: ArrayList<Llibre>.
	// Parametres de salida: no.
	public static void writeXML(ArrayList<Llibre> biblioteca) {
		try {

			DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
			DocumentBuilder build = dFact.newDocumentBuilder();
			Document doc = build.newDocument();
			Element raiz = doc.createElement("Biblioteca");
			doc.appendChild(raiz);

			for (Llibre l : biblioteca) {

				Element llibre = doc.createElement("llibre");
				String id = String.valueOf(l.getIdentificador());
				llibre.setAttribute("identificador", id);
				raiz.appendChild(llibre);
				Element titol = doc.createElement("titol");
				titol.appendChild(doc.createTextNode(l.getTitol()));
				llibre.appendChild(titol);
				Element autor = doc.createElement("autor");
				autor.appendChild(doc.createTextNode(l.getAutor()));
				llibre.appendChild(autor);
				Element anyPublicacio = doc.createElement("anyPublicacio");
				anyPublicacio.appendChild(doc.createTextNode(l.getAnyPublicacio()));
				llibre.appendChild(anyPublicacio);
				Element editorial = doc.createElement("editorial");
				editorial.appendChild(doc.createTextNode(l.getEditorial()));
				llibre.appendChild(editorial);
				Element numPaginas = doc.createElement("numPagines");
				numPaginas.appendChild(doc.createTextNode(l.getNumPagines()));
				llibre.appendChild(numPaginas);
			}

			TransformerFactory tranFactory = TransformerFactory.newInstance(); // Crear serializador
			Transformer aTransformer = tranFactory.newTransformer();
			aTransformer.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // Darle formato al documento
			aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			try {
				FileWriter fw = new FileWriter("biblioteca.xml"); // Definir el nombre del fichero y guardar
				StreamResult result = new StreamResult(fw);
				aTransformer.transform(source, result);
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (TransformerException ex) {
			System.out.println("Error escribiendo el documento");
		} catch (ParserConfigurationException ex) {
			System.out.println("Error construyendo el documento");
		}

	}

	// Metode: recuperarTots
	// Descripcio: retorna una llista amb tots el llibres que llig del xml
	// Parametres d'entrada: no.
	// Parametres de salida: ArrayList<Llibre>
	public static ArrayList<Llibre> recuperarTots() {
		ArrayList<Llibre> biblioteca = new ArrayList<Llibre>();
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document document = dBuilder.parse(new File("biblioteca.xml"));
			Element raiz = document.getDocumentElement();

			NodeList nodeList = document.getElementsByTagName("llibre");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;

					String identificador = eElement.getAttribute("identificador");
					String titol = eElement.getElementsByTagName("titol").item(0).getTextContent();
					String autor = eElement.getElementsByTagName("autor").item(0).getTextContent();
					String anyPublicacio = eElement.getElementsByTagName("anyPublicacio").item(0).getTextContent();
					String editorial = eElement.getElementsByTagName("editorial").item(0).getTextContent();
					String numPagines = eElement.getElementsByTagName("numPagines").item(0).getTextContent();

					biblioteca.add(new Llibre(identificador, titol, autor, anyPublicacio, editorial, numPagines));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return biblioteca;

	}

	// Metode: mostrarLlibre
	// Descripcio: mostra per pantalla els parametres de un llibre donat per
	// paremetre
	// Parametres d'entrada: Llibre
	// Parametres de salida: no.
	public static void mostrarLlibre(Llibre llibre) {
		System.out.println(llibre.getTitol() + ":");
		System.out.println("	- Identificador: " + llibre.getIdentificador());
		System.out.println("	- Autor: " + llibre.getAutor());
		System.out.println("	- AnyPublicacio: " + llibre.getAnyPublicacio());
		System.out.println("	- Editorial: " + llibre.getEditorial());
		System.out.println("	- NumPagines: " + llibre.getNumPagines());
	}

	// Metode: recuperarLlibre
	// Descripcio: retorna el llibre amb l'identificador que li pases per paremetre
	// Parametres d'entrada: Integer identificador
	// Parametres de salida: Llibre
	public static Llibre recuperarLlibre(Integer identificador) {
		for (Llibre l : recuperarTots()) {
			if (l.getIdentificador().equals(identificador.toString())) {
				return l;
			}
		}
		return null;
	}

	// Metode: crearLlibre
	// Descripcio: crea un nou llibre donat per paremetre
	// Parametres d'entrada: Llibre llibre
	// Parametres de salida: Integer identificador
	public static int crearLlibre(Llibre llibre) {
		ArrayList<Llibre> biblioteca = recuperarTots();
		biblioteca.add(llibre);
		writeXML(biblioteca);
		return Integer.parseInt(llibre.getIdentificador());
	}

	// Metode: borrarLlibre
	// Descripcio: borra un llibre a partir d'un identificador
	// Parametres d'entrada: Integer id
	// Parametres de salida: no.
	public static void borrarLlibre(Integer id) {
		ArrayList<Llibre> biblioteca = recuperarTots();
		for (int i = 0; i < biblioteca.size(); i++) {
			if (biblioteca.get(i).getIdentificador().equals(id.toString())) {
				biblioteca.remove(i);
			}
		}
		writeXML(biblioteca);
	}

	// Metode: actualitzarLlibre
	// Descripcio:lo que fa es actualitzar un
	// llibre a partir d'un identificador
	// Parametres d'entrada: Integer id
	// Parametres de salida: no.
	public static void actualitzaLlibre(Integer id) {
		ArrayList<Llibre> biblioteca = recuperarTots();
		Llibre modificar = biblioteca.get(id);

		Scanner sc = new Scanner(System.in);
		System.out.print("Titol (" + modificar.getTitol() + ") = ");
		modificar.setTitol(sc.nextLine());

		System.out.print("Autor: (" + modificar.getAutor() + ") = ");
		modificar.setAutor(sc.nextLine());

		System.out.print("Any de publicacio: (" + modificar.getAnyPublicacio() + ") = ");
		modificar.setAnyPublicacio(sc.nextLine());

		System.out.print("Editorial: (" + modificar.getEditorial() + ") = ");
		modificar.setEditorial(sc.nextLine());

		System.out.print("Nº Pagines: (" + modificar.getNumPagines() + ") = ");
		modificar.setNumPagines(sc.nextLine());

		writeXML(biblioteca);

	}

	// Metode: main
	// Descripcio: metode principal que gestiona el menu.
	// Parametres d'entrada: Array de Strings pasat per parametre.
	// Parametres de salida: no.
	public static void main(String[] args) {
		System.out.println("1. Mostrar tots els titols de la biblioteca");
		System.out.println("2. Mostrar informació detallada d'un llibre");
		System.out.println("3. Crear nou llibre");
		System.out.println("4. Actualitzar llibre");
		System.out.println("5. Borrar llibre");
		System.out.println("6. Tanca la biblioteca");

		Scanner sc = new Scanner(System.in);
		String resposta = sc.nextLine();

		switch (Integer.parseInt(resposta)) {
		case 1:
			System.out.println("====== Biblioteca ======");
			for (Llibre l : recuperarTots()) {
				System.out.println(l.getTitol() + "(" + l.getIdentificador() + ")");
			}
			break;
		case 2:
			System.out.print("Indica el id del llibre: ");
			Integer id = sc.nextInt();
			mostrarLlibre(recuperarLlibre(id));
			break;
		case 3:
			String identificador = Integer.toString(recuperarTots().size());
			System.out.print("Titol: ");
			String titol = sc.nextLine();
			System.out.print("Autor: ");
			String autor = sc.nextLine();
			System.out.print("Any de publicacio: ");
			String any = sc.nextLine();
			System.out.print("Editorial: ");
			String editorial = sc.nextLine();
			System.out.print("Nº Pagines: ");
			String numPagines = sc.nextLine();
			crearLlibre(new Llibre(identificador, titol, autor, any, editorial, numPagines));
			break;
		case 4:
			System.out.print("Indica el id del llibre: ");
			Integer id2 = sc.nextInt();
			actualitzaLlibre(id2);
			break;
		case 5:
			System.out.print("Indica el id del llibre: ");
			Integer id3 = sc.nextInt();
			borrarLlibre(id3);
			break;
		case 6:
			System.exit(0);
			break;
		default:
			break;
		}
	}

}
