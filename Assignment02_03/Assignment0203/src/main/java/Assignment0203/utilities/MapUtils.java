package Assignment0203.utilities;

import Assignment0203.Map;
import Assignment0203.RoadSegment;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;

import static Assignment0203.utilities.IRIs.*;

public class MapUtils {
    private static final Map mapData = Map.getInstance();

    /** Creates a road segment */
    public static void addRoadSegment() {
        OWLClass classRoadSegment = OntologyUtils.createClass(roadSegmentClassIRI);
        OWLClass classOneWayLane = OntologyUtils.createClass(oneWayLaneClassIRI);
        mapData.setClassRoadSegment(classRoadSegment);
        mapData.setClassOneWayLane(classOneWayLane);
        mapData.setRoadSegment(
                new RoadSegment(
                        OntologyUtils.createIndividualAndSetHisType(individualRoadSegment, classRoadSegment),
                        OntologyUtils.createIndividualAndSetHisType(individualRoadSegmentCarriagewayR, classOneWayLane),
                        OntologyUtils.createIndividualAndSetHisType(individualRoadSegmentCarriagewayL, classOneWayLane)
                )
        );
    }

    public static void connectObjectPropertiesToRoadSegment(OWLOntologyUtils ontology) {
        OWLObjectProperty hasLane = OntologyUtils.createObjectProperty(hasLaneIRI);
        OWLObjectProperty isLaneOf = OntologyUtils.createObjectProperty(isLaneOfIRI);
        mapData.setHasLane(hasLane);
        mapData.setIsLaneOf(isLaneOf);
        /* new axioms:
            hasLane is inverse of isLaneOf
            the road segment has a right carriageway and a left one
         */
        ontology.addAxiom(OntologyUtils.createInverseObjectProperty(hasLane, isLaneOf));
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                mapData.getRoadSegment().getRoadSegment(),
                hasLane,
                mapData.getRoadSegment().getCarriagewayR())
        );
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                mapData.getRoadSegment().getRoadSegment(),
                hasLane,
                mapData.getRoadSegment().getCarriagewayL())
        );
    }
}
