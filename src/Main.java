import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//initialize RDF Model, Input Scanner, and CRUD Classes
		Model _mainModel = ModelFactory.createDefaultModel();
		Scanner sc = new Scanner(System.in);
		Create _createUser = new Create();
		Update _updateUser = new Update();
		
		//initialize variables
		int option = 0;
		
		//main menu loop
		do {
			
			//main menu and read option
			System.out.println("Menu: ");
			System.out.println("1 - Create User");
			System.out.println("2 - Read Users in RDF/XML format");
			System.out.println("3 - Add know relationships");
			System.out.println("0 - Exit");
			System.out.println("Select an option: ");
			option = sc.nextInt();
			
			//create user
			if(option == 1) {
				_mainModel = _createUser.create();
			}
			
			//print RDF/XML
			if(option == 2) {
				_mainModel.write(System.out, "RDF/XML");
			}
			
			//add know relationship
			if(option == 3) {
				_mainModel = _updateUser.update(_mainModel);
			}
			
		}while(option != 0);
		
		sc.close();
		
	}

}
