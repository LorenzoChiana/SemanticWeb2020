package Assignment0203.utilities;

import Assignment0203.MyCar;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;

import static Assignment0203.utilities.IRIs.*;

public class MyCarUtils {
    private final static MyCar myCarData = MyCar.getInstance();

    /** Creates my car */
    public static void addMyCar() {
        myCarData.setMyCar(OntologyUtils.createClass(myCarIRI));
    }

    /** Connects data properties to my car */
    public  static void connectPropertiesToMyCar(OWLOntologyUtils ontology) {
        //Data properties
        OWLDataProperty dataPropertyCardID = OntologyUtils.createDataProperty(carIdIRI);
        myCarData.setCarId(dataPropertyCardID);
    }
}
