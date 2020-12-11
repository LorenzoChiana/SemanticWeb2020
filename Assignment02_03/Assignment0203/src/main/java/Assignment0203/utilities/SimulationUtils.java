package Assignment0203.utilities;

import Assignment0203.PathControl;
import Assignment0203.Map;
import Assignment0203.MyCar;
import Assignment0203.SpeedProfile;
import org.semanticweb.owlapi.model.*;

import java.util.*;

import static Assignment0203.utilities.IRIs.*;

public class SimulationUtils {

    /** Create a simulation to test compliance with speed limits */
    public static OWLOntologyUtils createSpeedLimitSimulation(boolean speedWarning) {
        OWLOntologyUtils myOnto = SimulationUtils.createMyOntology();
        initClasses(myOnto);
        createMyCarRoute(myOnto);
        createRunDirectionRule(myOnto);
        createSpeedLimitSWRLRule(myOnto, speedWarning);
        myOnto.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                MyCar.getInstance().getMyCarIndividual(),
                PathControl.getInstance().getOverSpeedWarningThan(),
                Map.getInstance().getSpeedLimit()
        ));
        return myOnto;
    }

    /** Creates a new ontology */
    private static OWLOntologyUtils createMyOntology() {
        //creation of new empty ontology
        OWLOntologyUtils onto = OntologyUtils.newEmptyOntology(myOntologyIRI).get();
        OntologyUtils.setOntology(onto);

        //import remote ontologies
        onto.importOntology(carOntoIRI);
        onto.importOntology(controlOntoIRI);
        onto.importOntology(mapOntoIRI);

        return onto;
    }

    /** Initialization of classes */
    private static void initClasses(OWLOntologyUtils onto) {
        //creation of road segment with 2 lanes
        MapUtils.addRoadSegment();
        MapUtils.connectObjectPropertiesToRoadSegment(onto);

        //creation of my car
        MyCarUtils.addMyCar();
        MyCarUtils.connectPropertiesToMyCar(onto);

        //creation class and properties for SWRL about speed
        PathControlUtils.addClassForSpeedAndGoForwardSWRLRules();
        PathControlUtils.connectPropertiesForSpeedSWRLRules();

        //set the speed profile and speed limit
        SpeedProfileUtils.addSpeedProfile();
        MapUtils.addSpeedLimit();
        MapUtils.connectSpeedMaxProperty(onto);
    }

    /** Creates new route */
    private static void createMyCarRoute(OWLOntologyUtils ontology) {
        List<OWLNamedIndividual> routeSequence = new ArrayList<>();
        routeSequence.add(Map.getInstance().getRoadSegmentStart().getLaneRight());
        routeSequence.add(Map.getInstance().getRoadSegmentStop().getLaneRight());
        PathControlUtils.createPath(ontology, routeSequence);
    }

    /** Creates SWRL rule for speedLimit */
    private static void createSpeedLimitSWRLRule(OWLOntologyUtils ontology, boolean speedWarning) {
        SWRLVariable varX = SWRLUtils.createSWRLVariable(varXIRI);
        SWRLVariable varY = SWRLUtils.createSWRLVariable(varYIRI);
        SWRLVariable varLane = SWRLUtils.createSWRLVariable(varLaneIRI);
        // Head's atom: body -> head
        SWRLClassAtom head = null;

        //Class atoms: OWL named class and a single argument representing an OWL individual
        // OnwWayLane(?Lane)
        SWRLClassAtom OneWayLane_Lane = SWRLUtils.createSWRLClassAtom(Map.getInstance().getClassOneWayLane(), varLane);
        // SpeedLimit(?Y)
        SWRLClassAtom SpeedLimit_Y = SWRLUtils.createSWRLClassAtom(Map.getInstance().getClassSpeedLimit(), varY);

        //Property Atoms: OWL object property and two arguments representing OWL individuals
        // isRunningOn(?X, ?Lane)
        SWRLObjectPropertyAtom isRunningOn_X_Lane = SWRLUtils.createSWRLObjectPropertyAtom(MyCar.getInstance().getIsRunningOn(), varX, varLane);
        // overSpeedWarningThan(?X, ?Y)
        SWRLObjectPropertyAtom overSpeedWarningThan_X_Y = SWRLUtils.createSWRLObjectPropertyAtom(PathControl.getInstance().getOverSpeedWarningThan(), varX, varY);

        // Body's atom: isRunningOn(?X, ?Lane) ^ OneWayLane(?Lane)
        Set<SWRLAtom> body = new HashSet<>();
        body.add(isRunningOn_X_Lane);
        body.add(OneWayLane_Lane);

        if(speedWarning) {
            // isRunningOn(?X, ?Lane) ^ OneWayLane(?Lane) ^ SpeedLimit(?Y) ^ overSpeedWarningThan(?X, ?Y)
            body.add(SpeedLimit_Y);
            body.add(overSpeedWarningThan_X_Y);
        }

        // isRunningOn(?X, ?Lane) ^ OneWayLane(?Lane) ^ SpeedLimit(?Y) ^ overSpeedWarningThan(?X, ?Y) -> constantSpeed(?X)
        // isRunningOn(?X, ?Lane) ^ OneWayLane(?Lane) -> acceleration(?X)
        head = (speedWarning) ? SWRLUtils.createSWRLClassAtom(SpeedProfile.getInstance().getConstantSpeed(), varX) : SWRLUtils.createSWRLClassAtom(SpeedProfile.getInstance().getAcceleration(), varX);

        // rule creation
        SWRLRule rule = SWRLUtils.createAnonymousSWRLRule(body, Collections.singleton(head));
        ontology.addAxiom(rule);
    }

    /** Created SWRL rule for run direction */
    public static void createRunDirectionRule(OWLOntologyUtils ontology) {
        SWRLVariable varX = SWRLUtils.createSWRLVariable(varXIRI);
        SWRLVariable varLane = SWRLUtils.createSWRLVariable(varLaneIRI);
        SWRLVariable varNextLane = SWRLUtils.createSWRLVariable(varNextLaneIRI);

        // isRunningOn(?X, ?Lane)
        SWRLObjectPropertyAtom isRunningOn_X_Lane = SWRLUtils.createSWRLObjectPropertyAtom(MyCar.getInstance().getIsRunningOn(), varX, varLane);

        // goStraightTo(?lane, ?nextLane)
        SWRLObjectPropertyAtom goStraightTo_Lane_NextLane = SWRLUtils.createSWRLObjectPropertyAtom(Map.getInstance().getGoStraightTo(), varLane, varNextLane);
        // GoForward(?X)
        SWRLClassAtom GoForward_X = SWRLUtils.createSWRLClassAtom(PathControl.getInstance().getGoForward(), varX);

        // nextPathSegment(?lane, ?nextLane)
        SWRLObjectPropertyAtom nextPathSegment_Lane_NextLane = SWRLUtils.createSWRLObjectPropertyAtom(PathControl.getInstance().getNextPathSegment(), varLane, varNextLane);

        // isRunningOn(?X, ?Lane) ^ goStraightTo(?lane, ?nextLane) ^ nextPathSegment(?lane, ?nextLane)
        Set<SWRLAtom> body = new HashSet<>();
        body.add(isRunningOn_X_Lane);
        body.add(goStraightTo_Lane_NextLane);
        body.add(nextPathSegment_Lane_NextLane);
        // isRunningOn(?X, ?Lane) ^ goStraightTo(?lane, ?nextLane) ^ nextPathSegment(?lane, ?nextLane) -> GoForward(?X)
        SWRLRule rule = SWRLUtils.createAnonymousSWRLRule(
                body,
                Collections.singleton(GoForward_X)
        );
        ontology.addAxiom(rule);
    }
}
