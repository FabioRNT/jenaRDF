import java.util.Scanner;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.sparql.vocabulary.FOAF;

public class Update {

	//initializing RDF model and Input Scanner
	Scanner sc = new Scanner(System.in);
	
	public Model update(Model _pModel) {
		
		//receive parameter from main class
		Model _model = _pModel;
		
		//fetch all names
		String _queryString = 
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
	            "SELECT ?name WHERE { " +
	            "    ?person foaf:name ?name . " +
	            "}";
		
		Query _query = QueryFactory.create(_queryString);
		QueryExecution _qexec = QueryExecutionFactory.create(_query, _model);
		ResultSet _rs = _qexec.execSelect();		
		
		//display a list of names
		System.out.println("List of Names: ");	
		while (_rs.hasNext()) {
            QuerySolution soln = _rs.nextSolution();
            Literal name = soln.getLiteral("name");
            System.out.println(name);
        }
		
		_qexec.close();
		
		//initialize strings and get the relationship that the user wants (validations missing!)
		String _name1, _name2 = "";
		
		System.out.println("Write the name of a user: ");
		_name1 = sc.nextLine();
	
		System.out.println("This user Knows: ");
		_name2 = sc.nextLine();
		
		//adds the know relationship
		_model.getResource("http://example.org/" + _name1).addProperty(FOAF.knows, "http://example.org/" + _name2);
		
		return _model;
	}
	
}
