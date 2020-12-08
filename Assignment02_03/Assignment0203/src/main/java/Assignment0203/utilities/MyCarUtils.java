package Assignment0203.utilities;

import Assignment0203.MyCar;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import static Assignment0203.utilities.IRIs.*;

public class MyCarUtils {
    private final static MyCar myCar = MyCar.getInstance();

    /** Creates my car */
    public static void addMyCar() {
        OWLClass myCarCLass = OntologyUtils.createClass(myCarIRI);
        myCar.setMyCar(myCarCLass);
        myCar.setMyCarIndividual(OntologyUtils.createIndividualAndSetHisType(individualMyCarIRI, myCarCLass));
    }

    /** Connects data properties to my car */
    public  static void connectPropertiesToMyCar(OWLOntologyUtils ontology) {
        OWLDataProperty carID = OntologyUtils.createDataProperty(carIdIRI);
        myCar.setCarId(carID);
        ontology.addAxiom(OntologyUtils.createDataPropertyAxiom(carID, myCar.getMyCarIndividual(), "0"));
    }

    /** Sets the initial position that my car running.
     * @param ontology OWLOntologyWithTools
     */
    public static void setMyCarPositionAndStartRunning(OWLOntologyUtils ontology, OWLNamedIndividual startNode) {
        OWLObjectProperty isRunningOn = OntologyUtils.createObjectProperty(isRunningOnIRI);
        myCar.setIsRunningOn(isRunningOn);
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(myCar.getMyCarIndividual(), isRunningOn, startNode));
    }
}
