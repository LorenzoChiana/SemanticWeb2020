package Assignment0203.utilities;

import Assignment0203.PathControl;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import java.util.List;

import static Assignment0203.utilities.IRIs.*;

public class PathControlUtils {
    private final static PathControl pathControl = PathControl.getInstance();

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
     * @param newSequence route from startNode to endNode.
     */
    public static void createPath(OWLOntologyUtils ontology, List<OWLNamedIndividual> newSequence) {
        OWLObjectProperty nextPathSegment = OntologyUtils.createObjectProperty(nextPathSegmentIRI);
        OWLDataProperty pathSegmentID = OntologyUtils.createDataProperty(pathSegmentIDIRI);
        OWLClass classStartNode = OntologyUtils.createClass(startNodeIRI);
        OWLClass classEndNode = OntologyUtils.createClass(endNodeIRI);

        pathControl.setNextPathSegment(nextPathSegment);
        pathControl.setStartLane(classStartNode);
        pathControl.setEndLane(classEndNode);

        //connect start and end road segments for the route
        ontology.addAxiom(OntologyUtils.createClassAssertionAxiom(classStartNode, newSequence.get(0)));
        ontology.addAxiom(OntologyUtils.createClassAssertionAxiom(classEndNode, newSequence.get(newSequence.size()-1)));

        pathControl.setPathSegmentID(pathSegmentID);
        for(int i = 0; i <= newSequence.size() - 1; i++) {
            // new axiom: the path segment i+1 is the nextPathSegment of path segment i
            if(i != newSequence.size() - 1){
                ontology.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(newSequence.get(i), nextPathSegment, newSequence.get(i+1)));
            }
            // new axiom: gives an ID to path segment i
            ontology.addAxiom(OntologyUtils.createDataPropertyAxiom(pathSegmentID, newSequence.get(i), Integer.toString(i)));
        }

        MyCarUtils.setMyCarPositionAndStartRunning(ontology, newSequence.get(0));
    }
}
