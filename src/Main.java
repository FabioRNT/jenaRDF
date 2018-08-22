import java.io.IOException;
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//initialize RDF Model, Input Scanner, and CRUD Classes
		Model _mainModel = ModelFactory.createDefaultModel();
		Scanner _sc = new Scanner(System.in);
		Create _createUser = new Create();
		Read _readUser = new Read();
		Update _updateUser = new Update();
		Delete _deleteUser = new Delete();
		Fuseki _fusekiRDF = new Fuseki();
		TDB _jenaTDB = new TDB();
		
		//fuseki dataset URI
		String _dsURI = "http://localhost:3030/ds/";
		
		//TDB Model name
		String _TDBmodel = "TDB-Model";
		
		//initialize variables
		int option = 0;
		
		//main menu loop
		do {
			
			//main menu and read option
			System.out.println("\n");
			System.out.println("Menu: ");
			System.out.println("1  - Create User");
			System.out.println("2  - Read Users");
			System.out.println("3  - Read Users in specific format");
			System.out.println("4  - Add know relationships");
			System.out.println("5  - Delete resources");
			System.out.println("6  - Upload RDF/XML to Fuseki");
			System.out.println("7  - Get RDF/XML from Fuseki");
			System.out.println("8  - Query by name from Fuseki");
			System.out.println("9  - Store model in TDB");
			System.out.println("10 - Retrieve model from TDB");
			System.out.println("0  - Exit");
			System.out.println("Select an option: ");;
			option = _sc.nextInt();
			
			//skip the nextint line
			_sc.nextLine();
			
			System.out.println("\n");
			
			//create user
			if(option == 1) {
				_mainModel = _createUser.create(_mainModel);
			}
			
			//print users
			if(option == 2) {
				_readUser.readAll(_mainModel);
			}
			
			//print RDF/XML
			if(option == 3) {
				int format = 0;
				
				System.out.println("Select a format: ");
				System.out.println("1  - RDF/XML");
				System.out.println("2  - JSON-LD");
				System.out.println("3  - Turtle");
				format = _sc.nextInt();	
				
				_sc.nextLine();
				System.out.println("\n");
				
				_readUser.readFormat(_mainModel, format);
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
				_fusekiRDF.uploadRDF(_mainModel, _dsURI);

			}
			
			//get RDF/XML
			if(option == 7) {
				_mainModel = _fusekiRDF.getRDF(_dsURI);

			}
			
			//query by name from Fuseki
			if(option == 8) {
				String _name = "";
				
				System.out.println("Write the user name: ");
				_name = _sc.nextLine();
				
				_fusekiRDF.selectUserByName(_dsURI, _name);

			}
			
			//store in TDB
			if(option == 9) {
				
				_jenaTDB.TDBStore(_mainModel, _TDBmodel);

			}
			
			//retrieve from TDB
			if(option == 10) {
				
				_mainModel = _jenaTDB.TDBRetrieve(_TDBmodel);

			}
			
		}while(option != 0);
		
		_sc.close();
		
	}

}
