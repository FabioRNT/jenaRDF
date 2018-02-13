import java.io.IOException;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//initialize RDF Model, Input Scanner, and CRUD Classes
		Model _mainModel = ModelFactory.createDefaultModel();
		Scanner sc = new Scanner(System.in);
		Create _createUser = new Create();
		Read _readUser = new Read();
		Update _updateUser = new Update();
		Delete _deleteUser = new Delete();
		Fuseki _fusekiRDF = new Fuseki();
		
		//initialize variables
		int option = 0;
		
		//main menu loop
		do {
			
			//main menu and read option
			System.out.println("\n");
			System.out.println("Menu: ");
			System.out.println("1 - Create User");
			System.out.println("2 - Read Users");
			System.out.println("3 - Read Users in RDF/XML format");
			System.out.println("4 - Add know relationships");
			System.out.println("5 - Delete resources");
			System.out.println("6 - Upload RDF/XML to Fuseki");
			System.out.println("7 - Get RDF/XML from Fuseki");
			System.out.println("0 - Exit");
			System.out.println("Select an option: ");;
			option = sc.nextInt();
			System.out.println("\n");
			
			//create user
			if(option == 1) {
				_mainModel = _createUser.create();
			}
			
			//print users
			if(option == 2) {
				_readUser.readAll(_mainModel);
			}
			
			//print RDF/XML
			if(option == 3) {
				_readUser.readRDFXML(_mainModel);
			}
			
			//add know relationship
			if(option == 4) {
				_mainModel = _updateUser.update(_mainModel);
			}
			
			//delete resources
			if(option == 5) {
				_mainModel = _deleteUser.delete(_mainModel);
			}
			
			//upload RDF/XML
			if(option == 6) {
					_fusekiRDF.uploadRDF(_mainModel, "http://localhost:3030/ds/");

			}
			
			//get RDF/XML
			if(option == 7) {
					_mainModel = _fusekiRDF.getRDF("http://localhost:3030/ds/data");

			}
			
		}while(option != 0);
		
		sc.close();
		
	}

}
