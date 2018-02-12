
import java.util.Scanner;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.RDF;

public class Create {
	
	Model _model = ModelFactory.createDefaultModel();
	Scanner sc = new Scanner(System.in);
		  
     public  Model create() {
    	 
    	 String _name = ""; 
    	 String _email = ""; 	 
    	  
    	 System.out.println("Name:");
    	 _name = sc.nextLine();
    	 
    	 System.out.println("E-mail:");
    	 _email = sc.nextLine();
    	 
	     Resource _person= ResourceFactory.createResource("http://example.org/" + _name);
	     _model.add (_person, RDF.type, FOAF.Person);
	     _model.add (_person, FOAF.name, _name);
	     _model.add (_person, FOAF.mbox, ResourceFactory.createResource("mailto:" + _email));
	     
	     return _model;
	     
     }
}
