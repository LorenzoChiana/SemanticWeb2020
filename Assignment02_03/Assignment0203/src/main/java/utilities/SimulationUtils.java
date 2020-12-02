package utilities;

import org.semanticweb.owlapi.model.IRI;

import java.util.Optional;

import static utilities.IRIs.*;

public class SimulationUtils {

    public static OWLOntologyUtil createMyControlDataOntology() {
        // Creation of new empty ontology
        OWLOntologyUtil onto = OntologyUtils.newEmptyOntology(myOntologyIRI).get();
        OntologyUtils.setOntology(onto);
        System.out.println("Step1: empty ontology");

        //import remote ontologies
        onto.importOntology(carOntoIRI);
        onto.importOntology(controlOntoIRI);
        onto.importOntology(mapOntoIRI);
        System.out.println("Step2: import remote ontologies ("+ onto.numImportedOntologies() +")");
        
        

        return onto;
    }
}
