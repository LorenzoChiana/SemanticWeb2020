package Assignment0203.utilities;

import Assignment0203.Map;
import Assignment0203.SegmentControl;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import static Assignment0203.utilities.IRIs.*;

public class SegmentControlUtils {
    private final static SegmentControl pathControl = SegmentControl.getInstance();

    /** Creates the classes for the swrl rules about speed and go forward */
    public static void addClassForSpeedAndGoForwardSWRLRules() {
        pathControl.setOverSpeedWarning(OntologyUtils.createClass(overSpeedWarningIRI));
        pathControl.setGoForward(OntologyUtils.createClass(goForwardIRI));
    }
    /** Creates the properties for the swrl rules about speed */
    public static void connectPropertiesForSpeedSWRLRules(){
        pathControl.setOverSpeedWarningThan(OntologyUtils.createObjectProperty(overSpeedWarningThanIRI));
    }

    /** Creates the route sequence (startNode to endNode)
     * @param ontology the ontology
     */
    public static void createPath(OWLOntologyUtils ontology) {
        OWLObjectProperty nextPathSegment = OntologyUtils.createObjectProperty(nextPathSegmentIRI);
        OWLDataProperty pathSegmentID = OntologyUtils.createDataProperty(pathSegmentIDIRI);
        OWLClass classStartNode = OntologyUtils.createClass(startNodeIRI);
        OWLClass classEndNode = OntologyUtils.createClass(endNodeIRI);
        OWLNamedIndividual startLane = Map.getInstance().getRoadSegmentStart().getLaneRight();
        OWLNamedIndividual endLane = Map.getInstance().getRoadSegmentStop().getLaneRight();

        pathControl.setNextPathSegment(nextPathSegment);
        pathControl.setStartLane(classStartNode);
        pathControl.setEndLane(classEndNode);

        //connect start and end road segments for the route
        ontology.addAxiom(OntologyUtils.createClassAssertionAxiom(classStartNode, startLane));
        ontology.addAxiom(OntologyUtils.createClassAssertionAxiom(classEndNode, endLane));

        pathControl.setPathSegmentID(pathSegmentID);
        ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(startLane, nextPathSegment, endLane));
        ontology.addAxiom(OntologyUtils.createDataPropertyAxiom(pathSegmentID, startLane, "0"));
        ontology.addAxiom(OntologyUtils.createDataPropertyAxiom(pathSegmentID, endLane, "1"));

        MyCarUtils.setMyCarPositionAndStartRunning(ontology, startLane);
    }
}
