import java.io.IOException;

import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;

public class Fuseki {

	
	public void uploadRDF(Model _pModel, String _pServiceURI)
	throws IOException {

		//get the param model and upload to the given URI 
		DatasetAccessor _accessor = DatasetAccessorFactory
				.createHTTP(_pServiceURI);
		_accessor.putModel(_pModel);
	}
	
	public Model getRDF(String _pServiceURI)
			throws IOException {
		
				Model _model = ModelFactory.createDefaultModel();

				//get a RDF model from the given URI
				DatasetAccessor _accessor = DatasetAccessorFactory
						.createHTTP(_pServiceURI);
				_model = _accessor.getModel();
				return _model;

	}
	
	public void selectUserByName(String _pServiceURI, String _pName) {
		String _queryString = 
			"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
		            "SELECT ?person ?mbox WHERE { " +
		            "    ?person foaf:name \"" + _pName + "\" . " +
		            "    ?person foaf:mbox ?mbox . " +
		            "}";
		
		QueryExecution _qexec = QueryExecutionFactory.sparqlService(_pServiceURI,
				_queryString);
		ResultSet _rs = _qexec.execSelect();
		
		if(!_rs.hasNext()) {
			System.out.println("User not found!");
		}
		else {
			while (_rs.hasNext()) {
				QuerySolution _soln = _rs.nextSolution();

				RDFNode _person = _soln.get("person");
				System.out.println("Person: " + _person);
				
				RDFNode _mail = _soln.get("mbox");
				System.out.println("Mail: " + _mail);
				
				String _queryStringKnow = 
						"PREFIX foaf: <http://xmlns.com/foaf/0.1/> " +
			            "SELECT ?know WHERE { " +	
			            "    ?person foaf:name \"" + _pName + "\" . " +
			            "    ?person foaf:knows ?know . " +
			            "}";
				
				QueryExecution _qexecKnow = QueryExecutionFactory.sparqlService(_pServiceURI,
						_queryStringKnow);
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
		}
		
	}
	
}
