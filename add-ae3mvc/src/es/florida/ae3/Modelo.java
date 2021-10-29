package es.florida.ae3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Modelo {

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
				// System.out.println("S");
				biblioteca.remove(i);
			}
		}
		writeXML(biblioteca);
	}

	// Metode: actualitzarLlibre
	// Descripcio: He tingut que cambiar un poquet la estructura de este metode para
	// que funciona amb el modelo vista controlador, lo que fa es actualitzar un
	// llibre a partir d'un identificador pero com açi no puc preguntar cada valor
	// l'il done directamente dels textFields a traves de parametre
	// Parametres d'entrada: String id, String titol, String autor, String any, String editorial
	// Parametres de salida: no.
	public static void actualitzaLlibre(String id, String titol, String autor, String any, String editorial,
			String pagines) {
		ArrayList<Llibre> biblioteca = recuperarTots();
		Llibre modificar = biblioteca.get(Integer.parseInt(id));

		modificar.setTitol(titol);

		modificar.setAutor(autor);

		modificar.setAnyPublicacio(any);

		modificar.setEditorial(editorial);

		modificar.setNumPagines(pagines);

		writeXML(biblioteca);

	}

}
