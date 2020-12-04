package Assignment0203.utilities;

import static Assignment0203.utilities.IRIs.*;

public class SimulationUtils {

    public static OWLOntologyUtils createMyControlDataOntology() {
        // Creation of new empty ontology
        OWLOntologyUtils onto = OntologyUtils.newEmptyOntology(myOntologyIRI).get();
        OntologyUtils.setOntology(onto);
        System.out.println("Step1: empty ontology");

        //import remote ontologies
        onto.importOntology(carOntoIRI);
        onto.importOntology(controlOntoIRI);
        onto.importOntology(mapOntoIRI);
        System.out.println("Step2: import remote ontologies ("+ onto.numImportedOntologies() +")");

        // Creation of road segment with 2 carriageway
        MapUtils.addRoadSegment();
        MapUtils.connectObjectPropertiesToRoadSegment(onto);
        return onto;
    }
}
