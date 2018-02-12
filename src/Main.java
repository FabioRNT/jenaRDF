import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Model _mainModel = ModelFactory.createDefaultModel();
		
		Create _createUser = new Create();
		
		_mainModel = _createUser.create();
		
		_mainModel.write(System.out, "RDF/XML");
		
		
	}

}
