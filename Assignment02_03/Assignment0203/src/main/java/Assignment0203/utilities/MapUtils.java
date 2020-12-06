package Assignment0203.utilities;

import Assignment0203.Map;
import Assignment0203.RoadSegment;
import org.semanticweb.owlapi.model.*;

import static Assignment0203.utilities.IRIs.*;

public class MapUtils {
    private static final Map mapData = Map.getInstance();

    /** Creates a road segment */
    public static void addRoadSegment() {
        OWLClass classRoadSegment = OntologyUtils.createClass(roadSegmentIRI);
        OWLClass classOneWayLane = OntologyUtils.createClass(oneWayLaneIRI);
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

    /** Connects object properties to road segment
     *
     * @param ontology the ontology
     */
    public static void connectObjectPropertiesToRoadSegment(OWLOntologyUtils ontology) {
        OWLObjectProperty hasLane = OntologyUtils.createObjectProperty(hasLaneIRI);
        OWLObjectProperty isLaneOf = OntologyUtils.createObjectProperty(isLaneOfIRI);
        mapData.setHasLane(hasLane);
        mapData.setIsLaneOf(isLaneOf);
        /* new axioms:
            hasLane is inverse of isLaneOf
            the road segment has a right lane and a left one
         */
        ontology.addAxiom(OntologyUtils.createInverseObjectProperty(hasLane, isLaneOf));
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                mapData.getRoadSegment().getRoad(),
                hasLane,
                mapData.getRoadSegment().getLaneRight())
        );
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                mapData.getRoadSegment().getRoad(),
                hasLane,
                mapData.getRoadSegment().getLaneLeft())
        );
    }

    /** Creates class for speed limit */
    public static void addSpeedLimit() {
        OWLClass speedLimitClass = OntologyUtils.createClass(speedLimitIRI);
        mapData.setClassSpeedLimit(speedLimitClass);
        mapData.setSpeedLimit(OntologyUtils.createIndividualAndSetHisType(individualSpeedLimit, speedLimitClass));
    }

    /** Connects the data property speedMax
     *
     * @param ontology the ontology
     */
    public static void connectSpeedMaxProperty(OWLOntologyUtils ontology) {
        OWLDataProperty speedMax = OntologyUtils.createDataProperty(speedMaxIRI);
        mapData.setSpeedMax(speedMax);
        // new axiom: 50km/h is the new speed limit
        ontology.addAxiom(OntologyUtils.createDataPropertyAxiom(
                speedMax,
                mapData.getSpeedLimit(),
                "50"
        ));
    }
}
