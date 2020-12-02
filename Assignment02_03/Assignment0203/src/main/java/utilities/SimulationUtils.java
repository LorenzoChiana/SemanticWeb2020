package utilities;

import org.semanticweb.owlapi.model.IRI;

import java.util.Optional;

public class SimulationUtils {
    public final static String myOntologyIRI = "http://prova/myontology";
    // CarOnto, ControlOnto and MapOnto url
    private final static IRI carOntoIRI = IRI.create("http://ri-www.nii.ac.jp/ADAS/CarOnto.owl");
    private final static IRI controlOntoIRI = IRI.create("http://ri-www.nii.ac.jp/ADAS/ControlOnto.owl");
    private final static IRI mapOntoIRI = IRI.create("http://ri-www.nii.ac.jp/ADAS/MapOnto.owl");

    public static OWLOntologyUtil createMyPathDataOntology() {
        // Creation of new empty ontology
        OWLOntologyUtil onto = OntologyUtils.newEmptyOntology(IRI.create(myOntologyIRI)).get();
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
