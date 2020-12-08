package Assignment0203.utilities;

import Assignment0203.Map;
import Assignment0203.RoadSegment;
import org.semanticweb.owlapi.model.*;

import static Assignment0203.utilities.IRIs.*;

public class MapUtils {
    private static final Map map = Map.getInstance();

    /** Creates a road segment */
    public static void addRoadSegment() {
        OWLClass classRoadSegment = OntologyUtils.createClass(roadSegmentIRI);
        OWLClass classOneWayLane = OntologyUtils.createClass(oneWayLaneIRI);
        map.setClassRoadSegment(classRoadSegment);
        map.setClassOneWayLane(classOneWayLane);
        map.setRoadSegment(
                new RoadSegment(
                        OntologyUtils.createIndividualAndSetHisType(individualRoadSegment, classRoadSegment),
                        OntologyUtils.createIndividualAndSetHisType(individualRoadSegmentLaneR, classOneWayLane),
                        OntologyUtils.createIndividualAndSetHisType(individualRoadSegmentLaneL, classOneWayLane)
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
        OWLObjectProperty goStraightTo = OntologyUtils.createObjectProperty(goStraightToIRI);
        map.setHasLane(hasLane);
        map.setIsLaneOf(isLaneOf);
        map.setGoStraightTo(goStraightTo);
        /* new axioms:
            hasLane is inverse of isLaneOf
            the road segment has a right lane and a left one
         */
        ontology.addAxiom(OntologyUtils.createInverseObjectProperty(hasLane, isLaneOf));
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                map.getRoadSegment().getRoad(),
                hasLane,
                map.getRoadSegment().getLaneRight())
        );
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                map.getRoadSegment().getRoad(),
                hasLane,
                map.getRoadSegment().getLaneLeft())
        );

        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                map.getRoadSegment().getLaneRight(),
                goStraightTo,
                map.getRoadSegment().getLaneRight()
        ));
    }

    /** Creates class for speed limit */
    public static void addSpeedLimit() {
        OWLClass speedLimitClass = OntologyUtils.createClass(speedLimitIRI);
        map.setClassSpeedLimit(speedLimitClass);
        map.setSpeedLimit(OntologyUtils.createIndividualAndSetHisType(individualSpeedLimit, speedLimitClass));
    }

    /** Connects the data property speedMax
     *
     * @param ontology the ontology
     */
    public static void connectSpeedMaxProperty(OWLOntologyUtils ontology) {
        OWLDataProperty speedMax = OntologyUtils.createDataProperty(speedMaxIRI);
        map.setSpeedMax(speedMax);
        // new axiom: 50km/h is the new speed limit
        ontology.addAxiom(OntologyUtils.createDataPropertyAxiom(
                speedMax,
                map.getSpeedLimit(),
                "50"
        ));
    }
}
