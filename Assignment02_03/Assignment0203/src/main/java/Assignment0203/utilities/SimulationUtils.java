package Assignment0203.utilities;

import Assignment0203.Map;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import java.util.ArrayList;
import java.util.List;

import static Assignment0203.utilities.IRIs.*;

public class SimulationUtils {

    public static OWLOntologyUtils createMyControlDataOntology() {
        //creation of new empty ontology
        OWLOntologyUtils onto = OntologyUtils.newEmptyOntology(myOntologyIRI).get();
        OntologyUtils.setOntology(onto);
        System.out.println("Step1: empty ontology");

        //import remote ontologies
        onto.importOntology(carOntoIRI);
        onto.importOntology(controlOntoIRI);
        onto.importOntology(mapOntoIRI);
        System.out.println("Step2: import remote ontologies ("+ onto.numImportedOntologies() +")");

        //creation of road segment with 2 carriageway
        MapUtils.addRoadSegment();
        MapUtils.connectObjectPropertiesToRoadSegment(onto);

        //creation class and properties for SWRL about speed
        ControlUtils.addClassForSpeedSWRLRules();
        ControlUtils.connectPropertiesForSpeedSWRLRules();

        //creation of my car
        MyCarUtils.addMyCar();
        MyCarUtils.connectPropertiesToMyCar(onto);

        //set the speed profile and speed limit
        SpeedProfileUtils.addSpeedProfile();
        MapUtils.addSpeedLimit();
        MapUtils.connectSpeedMaxProperty(onto);
        return onto;
    }

    public static void createMyCarRoute(OWLOntologyUtils ontology) {
        List<OWLNamedIndividual> routeSequence = new ArrayList<>();
        routeSequence.add(Map.getInstance().getRoadSegment().getLaneRight());
        routeSequence.add(Map.getInstance().getRoadSegment().getLaneLeft());
        ControlUtils.createPath(ontology, routeSequence);
    }
}
