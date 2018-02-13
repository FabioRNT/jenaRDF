import java.io.IOException;

import org.apache.jena.query.DatasetAccessor;
import org.apache.jena.query.DatasetAccessorFactory;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Fuseki {

	
	public void uploadRDF(Model _pModel, String _serviceURI)
	throws IOException {

		//get the param model and upload to the given URI 
		DatasetAccessor _accessor = DatasetAccessorFactory
				.createHTTP(_serviceURI);
		_accessor.putModel(_pModel);
	}
	
	public Model getRDF(String _serviceURI)
			throws IOException {
		
				Model _model = ModelFactory.createDefaultModel();

				//get a RDF model from the given URI
				DatasetAccessor _accessor = DatasetAccessorFactory
						.createHTTP(_serviceURI);
				_model = _accessor.getModel();
				return _model;

			}
	
}
