import java.util.Scanner;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;

public class Read {
	
	//initializing Input Scanner
		Scanner sc = new Scanner(System.in);
		
		public void readRDFXML(Model _pModel) {
			
			_pModel.write(System.out, "RDF/XML");
			
		}
		
		public Model readAll(Model _pModel) {
			
			//receive parameter from main class
			Model _model = _pModel;
			
			//fetch all names
			String _queryString = 
					"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
		            "SELECT ?name ?mbox WHERE { " +
		            "    ?person foaf:mbox ?mbox . " +
		            "    ?person foaf:name ?name . " +
		            "}";
			
			Query _query = QueryFactory.create(_queryString);
			QueryExecution _qexec = QueryExecutionFactory.create(_query, _model);
			ResultSet _rs = _qexec.execSelect();		
			
			//display a list of subjects
			System.out.println("List of Subjects: ");
			System.out.println("\n");
			int i = 1;
			while (_rs.hasNext()) {			
	            QuerySolution _soln = _rs.nextSolution();
	            Literal _name = _soln.getLiteral("name");
	            Resource _mail = _soln.getResource("mbox");
	            System.out.println("User #" + i++);
	            System.out.println("\n");
	            System.out.println("Name: " + _name);
	            System.out.println("Mail: " + _mail);
	            
	            String _queryStringKnow = 
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
			            "SELECT ?know WHERE { " +
			            "    ?person foaf:name \"" + _name + "\" . " +		
			            "    ?person foaf:knows ?know . " +
			            "}";
				
				Query _queryKnow = QueryFactory.create(_queryStringKnow);
				QueryExecution _qexecKnow = QueryExecutionFactory.create(_queryKnow, _model);
				ResultSet _rsKnow = _qexecKnow.execSelect();
				
				while (_rsKnow.hasNext()) {
					QuerySolution _solnKnow = _rsKnow.nextSolution();
		            Literal _know = _solnKnow.getLiteral("know");
		            System.out.println("Know: " + _know);
				}
				System.out.println("\n");
				
				_qexecKnow.close();
				
	        }
			
			_qexec.close();
			
			return _model;
		}

}
