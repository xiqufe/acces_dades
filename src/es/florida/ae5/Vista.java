package es.florida.ae5;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Vista {

	private static JFrame frame;
	private static JTextField textField_Identificador;
	private static JButton btn1;
	private static JButton btn2;
	private static JButton btn3;
	private static JButton btn4;
	private static JButton btn5;
	private static JButton btn6;
	private static JTextField textField_Titol;
	private static JTextField textField_Autor;
	private static JTextField textField_AnyPublicacio;
	private static JTextField textField_Editorial;
	private static JTextField textField_numPagines;

	static Session s;

	// Metode: recuperarTots
	// Descripcio: retorna una llista amb tots el llibres que llig de la base de
	// dades
	// Parametres d'entrada: no.
	// Parametres de salida: ArrayList<Llibre>
	public static ArrayList<Llibre> recuperarTots() {
		s.beginTransaction();
		ArrayList<Llibre> biblioteca = new ArrayList<Llibre>();
		List listaLlibres = new ArrayList();
		listaLlibres = s.createQuery("FROM Llibre").list();
		for (Object o : listaLlibres) {
			Llibre l = (Llibre) o;
			biblioteca.add(l);
		}
		s.getTransaction().commit();
		s.clear();
		return biblioteca;
	}

	// Metode: mostrarLlibre
	// Descripcio: mostra per consola els parametres de un llibre donat per
	// paremetre
	// Parametres d'entrada: Llibre
	// Parametres de salida: no.
	public static void mostrarLlibre(String id) {
		for (Llibre llibre : recuperarTots()) {
			if (llibre.getIdentificador() == Integer.parseInt(id)) {
				System.out.println(llibre.getTitol() + ":");
				System.out.println("	- Identificador: " + llibre.getIdentificador());
				System.out.println("	- Autor: " + llibre.getAutor());
				System.out.println("	- AnyPublicacio: " + llibre.getAnyPublicacio());
				System.out.println("	- Editorial: " + llibre.getEditorial());
				System.out.println("	- NumPagines: " + llibre.getNumPagines());
			}
		}
	}

	// Metode: crearLlibre
	// Descripcio: crea un nou llibre donat per paremetre
	// Parametres d'entrada: Llibre llibre
	// Parametres de salida: no.
	public static void crearLlibre(Llibre llibre) {
		s.beginTransaction();
		Serializable id = s.save(llibre);
		s.getTransaction().commit();
		s.clear();
	}

	// Metode: actualitzaLlibre
	// Descripcio: He tingut que cambiar un poquet la estructura de este metode para
	// que funciona amb l'interficie grafica, lo que fa es actualitzar un
	// llibre a partir d'un identificador pero com açi no puc preguntar cada valor
	// l'il done directamente dels textFields a traves de parametre
	// Parametres d'entrada: Integer id, String titol, String autor, String any,
	// String editorial
	// Parametres de salida: no.
	public static void actualitzaLlibre(Integer id, String titol, String autor, String any, String editorial,
			String pagines) {
		s.beginTransaction();
		Llibre modificar = (Llibre) s.get(Llibre.class, id);

		modificar.setTitol(titol);

		modificar.setAutor(autor);

		modificar.setAnyPublicacio(any);

		modificar.setEditorial(editorial);

		modificar.setNumPagines(pagines);

		s.update(modificar);
		s.getTransaction().commit();
		s.clear();

	}

	// Metode: borrarLlibre
	// Descripcio: borra un llibre a partir d'un identificador
	// Parametres d'entrada: Integer id
	// Parametres de salida: no.
	public static void borrarLlibre(Integer id) {
		s.beginTransaction();
		Llibre llibre = new Llibre();
		llibre.setIdentificador(id);
		s.delete(llibre);
		s.getTransaction().commit();
		s.clear();
	}

	// Metode: Main
	// Descripcio: metode principal que carrega la configuracio i crea la sessio i
	// lança l'interficie grafica
	// Parametres d'entrada: String[]
	// Parametres de salida: no.
	public static void main(String[] args) {
		// Carrega la configuracio i crea un session factory
		Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
		configuration.addClass(Llibre.class);
		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
				.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);

		// Obri una nova sessió de la session factory
		s = sessionFactory.openSession();
		initialize();
	}

	// Metode: initialize
	// Descripcio: metode que gestiona la creacio de la aplicacio a nivel visual.
	// Parametres d'entrada: no.
	// Parametres de salida: no.
	private static void initialize() {
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
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cadena = "";
				for (Llibre l : recuperarTots()) {
					cadena += "=== " + l.getTitol() + " ===" + "\n";
					cadena += "id: " + l.getIdentificador() + "\n";
				}
				mostrarMensaje(cadena);
			}
		});
		btn1.setBounds(10, 42, 233, 23);
		frame.getContentPane().add(btn1);

		btn2 = new JButton("Mostrar informacio detallada d'un llibre");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer identificador = Integer.parseInt(getTextField_Identificador().getText());
				Llibre llibre = (Llibre) s.get(Llibre.class, identificador);

				getTextField_Titol().setText(llibre.getTitol());
				getTextField_Autor().setText(llibre.getAutor());
				getTextField_AnyPublicacio().setText(llibre.getAnyPublicacio());
				getTextField_Editorial().setText(llibre.getEditorial());
				getTextField_numPagines().setText(llibre.getNumPagines());
			}
		});
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
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Integer Identificador = recuperarTots().size() + 1;
				String Titol = getTextField_Titol().getText();
				String Autor = getTextField_Autor().getText();
				String AnyPublicacio = getTextField_AnyPublicacio().getText();
				String Editorial = getTextField_Editorial().getText();
				String NumPagines = getTextField_numPagines().getText();

				Llibre llibrenou = new Llibre(Titol, Autor, AnyPublicacio, Editorial, NumPagines);
				crearLlibre(llibrenou);
				mostrarMensaje("Se ha creat el llibre amb l'identificador " + Identificador);

				// Pose en el textField del Identificador el identificador que li correspon al
				// nou llibre
				getTextField_Identificador().setText(Identificador.toString());
			}
		});
		btn3.setBounds(10, 110, 233, 23);
		frame.getContentPane().add(btn3);

		btn4 = new JButton("Actualitzar llibre");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer Identificador = Integer.parseInt(getTextField_Identificador().getText());
				String Titol = getTextField_Titol().getText();
				String Autor = getTextField_Autor().getText();
				String AnyPublicacio = getTextField_AnyPublicacio().getText();
				String Editorial = getTextField_Editorial().getText();
				String NumPagines = getTextField_numPagines().getText();

				actualitzaLlibre(Identificador, Titol, Autor, AnyPublicacio, Editorial, NumPagines);
				;
				mostrarMensaje("Se ha modificat el llibre amb l'identificador " + Identificador);
			}
		});
		btn4.setBounds(10, 144, 233, 23);
		frame.getContentPane().add(btn4);

		btn5 = new JButton("Borrar llibre");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer Identificador = Integer.parseInt(getTextField_Identificador().getText());
				borrarLlibre(Identificador);

				mostrarMensaje("Se ha eliminat el llibre amb l'identificador " + Identificador);
			}
		});
		btn5.setBounds(10, 179, 233, 23);
		frame.getContentPane().add(btn5);

		btn6 = new JButton("Tancar la biblioteca");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn6.setBounds(10, 213, 233, 23);
		frame.getContentPane().add(btn6);
		frame.setVisible(true);
	}

// Metode: getTextField_Idenfitifcador
// Descripcio: metode retorna el objecte TextField_Identificador.
// Parametres d'entrada: no.
// Parametres de salida: JTextField textField_Identificador
	public static JTextField getTextField_Identificador() {
		return textField_Identificador;
	}

// Metode: getBtn1
// Descripcio: fa referencia a la 1 opcio del programa en consola.
// Parametres d'entrada: no.
// Parametres de salida: JButton btn1
	public static JButton getBtn1() {
		return btn1;
	}

// Metode: getBtn2
// Descripcio: fa referencia a la 2 opcio del programa en consola.
// Parametres d'entrada: no.
// Parametres de salida: JButton btn2
	public static JButton getBtn2() {
		return btn2;
	}

// Metode: getBtn3
// Descripcio: fa referencia a la 3 opcio del programa en consola.
// Parametres d'entrada: no.
// Parametres de salida: JButton btn3
	public static JButton getBtn3() {
		return btn3;
	}

// Metode: getBtn4
// Descripcio: fa referencia a la 4 opcio del programa en consola.
// Parametres d'entrada: no.
// Parametres de salida: JButton btn4
	public static JButton getBtn4() {
		return btn4;
	}

// Metode: getBtn5
// Descripcio: fa referencia a la 5 opcio del programa en consola.
// Parametres d'entrada: no.
// Parametres de salida: JButton btn5
	public static JButton getBtn5() {
		return btn5;
	}

// Metode: getBtn6
// Descripcio: fa referencia a la 6 opcio del programa en consola.
// Parametres d'entrada: no.
// Parametres de salida: JButton btn6
	public static JButton getBtn6() {
		return btn6;
	}

// Metode: getTextField_Titol
// Descripcio: metode retorna el objecte TextField_Titol.
// Parametres d'entrada: no.
// Parametres de salida: JTextField textField_Titol
	public static JTextField getTextField_Titol() {
		return textField_Titol;
	}

// Metode: getTextField_Autor
// Descripcio: metode retorna el objecte TextField_Autor.
// Parametres d'entrada: no.
// Parametres de salida: JTextField textField_Autor
	public static JTextField getTextField_Autor() {
		return textField_Autor;
	}

// Metode: getTextField_AnyPublicacio
// Descripcio: metode retorna el objecte TextField_AnyPublicacio.
// Parametres d'entrada: no.
// Parametres de salida: JTextField textField_AnyPublicacio
	public static JTextField getTextField_AnyPublicacio() {
		return textField_AnyPublicacio;
	}

// Metode: getTextField_Editorial
// Descripcio: metode retorna el objecte textField_Editorial.
// Parametres d'entrada: no.
// Parametres de salida: JTextField textField_Editorial
	public static JTextField getTextField_Editorial() {
		return textField_Editorial;
	}

// Metode: getTextField_numPagines
// Descripcio: metode retorna el objecte textField_numPagines.
// Parametres d'entrada: no.
// Parametres de salida: JTextField textField_numPagines
	public static JTextField getTextField_numPagines() {
		return textField_numPagines;
	}

// Metode: mostrarMensaje
// Descripcio: metode retorna una ventana amb el missatge que li pasem per parametre.
// Parametres d'entrada: un String que es el missatge
// Parametres de salida: no.
	public static void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(new JFrame(), mensaje, "INFO", JOptionPane.INFORMATION_MESSAGE);
	}

}
