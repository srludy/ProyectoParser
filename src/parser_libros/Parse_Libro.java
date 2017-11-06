package parser_libros;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Parse_Libro {

		static Parser parser;

		public static void main(String[] args) {
	
		int opcion = 0;
		
			do {
				Scanner sc = new Scanner(System.in);

				System.out.println("Elije una opcion");
				System.out.println("1: Parsear un xml.");
				System.out.println("2: Serializar un Objetuo a un xml.");
				System.out.println("3: Salir.");
				opcion = sc.nextInt();
				
				switch(opcion) {
				case 1: parseXml(); opcion = 0; break;
				case 2: serializaObjecto(); opcion = 0; break;
				default: System.out.println("Elije una opcion valida.");
				case 3: System.out.println("Cerrado.");break;
				
				}
			}while(opcion == 0 || opcion != 3 );
			
		}
		
		
		private static void serializaObjecto() {
			Scanner sc = new Scanner(System.in);

			System.out.println("Introduce el nombre del fichero al que quieres volcar tu xml.");
			String nomFichero = sc.nextLine();
			File file = new File(nomFichero+".xml");
			if(parser != null) {
				if(!file.exists()) {
					try {
						ArrayList<Libro> libreria = parser.getLibros();	
						MarshallerLibro mLibro = new MarshallerLibro(libreria);
						mLibro.newDocument();
						mLibro.buildDomStructure();
						mLibro.buildDomToXml(file);
						System.out.println("Generado XML correctamente.");
					} catch (ParserConfigurationException e){
						System.err.println("Error parseando el documento.");
					} catch (IOException e) {
						System.err.println("Error de ejecucion del programa.");
					} catch (TransformerException e) {
						System.err.println("Error Transformando el documento en XML ");
					}
				}else {
					System.err.println("El fichero ya existe.");
				}

			}else {
				System.err.println("Necesitas parsear algun documento XML en el programa primero.");
			}
			
		}


		public static void parseXml() {
			Scanner sc = new Scanner(System.in);
			System.out.println("Introduce el nombre del xml que quieres introducir al sistema.");
			String nomFichero = sc.nextLine();
			File file = new File(nomFichero+".xml");
			if(file.exists()) {
				try {			
					parser = new Parser();
					parser.parseFicheroXml(nomFichero+".xml");
					parser.parseDocument();
					parser.print();
					
				} catch (IOException e) {
					System.err.println("Error de ejecucion.");
				} catch (ParserConfigurationException e) {
					System.err.println("Error de configuracion del parse.");
				} catch (SAXException e) {
					System.err.println("Error parseando el fichero XML");
				}
			}else {
				System.err.println("El fichero existe.");
			}
		}
		
	}