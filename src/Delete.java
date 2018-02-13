import java.util.Scanner;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

public class Delete {
	
	//initializing Input Scanner
	Scanner _sc = new Scanner(System.in);
	
	public Model delete(Model _pModel) {
			
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
		System.out.println("\n");
		while (_rs.hasNext()) {
            QuerySolution _soln = _rs.nextSolution();
            Literal _name = _soln.getLiteral("name");
            System.out.println("Name: " + _name);
        }
		
		_qexec.close();
		
		//initialize string and read the user to be deleted(validations missing!)
		String _name = "";
		
        System.out.println("\n");
		System.out.println("Write the name of the user to be deleted: ");
		_name = _sc.nextLine();
		
		Resource _delResource = _model.getResource("http://example.org/" + _name);
		
		//confirmation
		String _confirm = "";
		
		System.out.println("Are you sure? (Y/N)");
		_confirm = _sc.nextLine().toUpperCase();
		
		if(_confirm.equals("Y")) {
			//remove statements where resource is subject
			_model.removeAll(_delResource, null, (RDFNode) null);
			//remove statements where resource is object
			_model.removeAll(null, null, _model.createLiteral(_delResource.toString()));
		}
		
		
		return _model;
	}	

}
