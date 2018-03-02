import org.apache.jena.query.Dataset;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.tdb.TDBFactory;
import org.apache.jena.tdb.base.file.Location;

public class TDB {

	Location _location = Location.create("target/TDB");
	Dataset _ds = TDBFactory.createDataset(_location);

	public void TDBStore(Model _pModel, String _pModelName) {


		_ds.begin(ReadWrite.WRITE);
		try {
			_ds.replaceNamedModel(_pModelName, _pModel);
			_ds.commit();
		} catch (Exception e) {
			_ds.abort();
		} finally {
			_ds.end();
		}

	}

	public Model TDBRetrieve(String _pModelName) {

		Model _TDBmodel = null;
		Model _model = ModelFactory.createDefaultModel();

		_ds.begin(ReadWrite.READ);
		try {
			_TDBmodel = _ds.getNamedModel(_pModelName);
			_model.add(_TDBmodel);
			_ds.commit();
		} finally {
			_ds.end();
		}
		
		return _model;

	}
}
