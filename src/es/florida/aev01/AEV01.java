package es.florida.aev01;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class AEV01 {
	
	public static void Menu() {
		//Comands:
		System.out.println("Comands:");
		System.out.println("	- getInformacio");
		System.out.println("	- creaCarpeta");
		System.out.println("	- creaFitxer");
		System.out.println("	- elimina");
		System.out.println("	- renomena");
		System.out.println("");
	}
	
	public static void getInformacio(File f) {
		//Gestio de errors
		try {
		//Nom
		System.out.println("Nom: "+f.getName());
		//Es directori o fitxer?
		if(f.isFile()) {
			System.out.println("Es un fitxer.");
		} else {
			System.out.println("Es un directori.");
		}
		//Ubicacio
		System.out.println("Ubicacio: "+f.getAbsolutePath());
		//Ultima modificacio
		Long data = f.lastModified();
		Date d = new Date(data);
		System.out.println("Ultima Modificacio: "+d);
		//Ocult?
		System.out.println("Ocult?: "+f.isHidden());
		
		//Si es fitxer
		if(f.isFile()) {
			//Grandaria
			Long grandaria = f.length();
			System.out.println("Grandaria: "+grandaria+" Bytes");
		} else {
			//Si es directori
			//Llista archius
			String[] archius = f.list();
			System.out.println("Archius:");
			for(String s:archius) {
				System.out.println("	- "+s);
			}
			//Espai lliure
			long espailliure = f.getFreeSpace();
			System.out.println("Espai lliure: "+(espailliure/1024/1024/1024)+" Gb");
			//Espai ocupat
			long espaiocupat =f.getTotalSpace()-f.getFreeSpace();
			System.out.println("Espai ocupat: "+(espaiocupat/1024/1024/1024)+" Gb");
			//Espai total
			long espaitotal = f.getUsableSpace();
			System.out.println("Espai total: "+(espaitotal/1024/1024/1024)+" Gb");
		}
		
		} catch(SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void creaCarpeta(File f) {
		//Definim Scanner
		Scanner sc = new Scanner(System.in);
		//Preguntem nom de la carpeta
		System.out.print("Dime un nom de carpeta: ");
		String nombre = sc.nextLine();
		
		//Creem carpeta
		//Gestio de errors
		try {
		File carpeta = new File(f.getPath()+"/"+nombre);
		carpeta.mkdir();
		} catch(SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void creaFitxer(File f) {
		//Definim Scanner
		Scanner sc = new Scanner(System.in);
		//Preguntem nom del archiu
		System.out.print("Dime un nom de archiu: ");
		String nombre = sc.nextLine();
		
		//Creem archiu
		//Gestio de errors
		try {
			File archiu = new File(f.getPath()+"/"+nombre);
			archiu.createNewFile();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void elimina(File f) {
		//Definim Scanner
		Scanner sc = new Scanner(System.in);
		//Preguntem nom de la carpeta
		System.out.print("Dime un nom de carpeta o archiu per a borrar: ");
		String nombre = sc.nextLine();
		
		//Eliminem
		//Gestio de errors
		try {
		File directorio = new File(f.getPath()+"/"+nombre);
		directorio.delete();
		}catch(SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void renomena(File f) {
		//Definim Scanner
		Scanner sc = new Scanner(System.in);
		//Preguntem nom de la carpeta
		System.out.print("Dime un nom de carpeta o archiu per a renombrar: ");
		String nombre = sc.nextLine();
		System.out.print("Dime un nom per a renombrar: ");
		String nombrenou = sc.nextLine();
		
		//Renombrem
		//Gestio de errors
		try {
		File directorioantic = new File(f.getPath()+"/"+nombre);
		File directorionou = new File(f.getPath()+"/"+nombrenou);
		directorioantic.renameTo(directorionou);
		
		}catch(SecurityException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//Definim directori
		String dir = args[0];
		File directorio = new File(dir);
		
		//Mostrem opcions Menu
		Menu();
		//Preguntem que opcio querem
		System.out.println("¿Que opcion queremos?");
		
		//Definim Scanner
		Scanner sc = new Scanner(System.in);
		String opcion = sc.nextLine();
		
		switch(opcion) {
		case "getInformacio": getInformacio(directorio);
		break;
		case "creaCarpeta": creaCarpeta(directorio);
		break;
		case "creaFitxer": creaFitxer(directorio);
		break;
		case "elimina": elimina(directorio);
		break;
		case "renomena": renomena(directorio);
		break;
		default: System.out.println("Comando introduit invalid.");
		break;
		}

		
		}

}