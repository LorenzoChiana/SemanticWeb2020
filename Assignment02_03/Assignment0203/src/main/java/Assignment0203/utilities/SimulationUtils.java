package Assignment0203.utilities;

import Assignment0203.SegmentControl;
import Assignment0203.Map;
import Assignment0203.MyCar;
import Assignment0203.SpeedProfile;
import org.semanticweb.owlapi.model.*;

import java.util.*;

import static Assignment0203.utilities.IRIs.*;

public class SimulationUtils {

    /** Create the simulation */
    public static OWLOntologyUtils createSimulation(boolean speedWarning) {
        OWLOntologyUtils myOnto = SimulationUtils.createMyOntology();
        initClasses(myOnto);
        SegmentControlUtils.createPath(myOnto);
        createRunDirectionSWRLRule(myOnto);
        createSpeedLimitSWRLRule(myOnto, speedWarning);
        myOnto.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                MyCar.getInstance().getMyCarIndividual(),
                SegmentControl.getInstance().getOverSpeedWarningThan(),
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
        SegmentControlUtils.addClassForSpeedAndGoForwardSWRLRules();
        SegmentControlUtils.connectPropertiesForSpeedSWRLRules();

        //set the speed profile and speed limit
        SpeedProfileUtils.addSpeedProfile();
        MapUtils.addSpeedLimit();
        MapUtils.connectSpeedMaxProperty(onto);
    }

    /** Creates SWRL rule for speedLimit */
    private static void createSpeedLimitSWRLRule(OWLOntologyUtils ontology, boolean speedWarning) {
        SWRLVariable varX = SWRLUtils.createSWRLVariable(varXIRI);
        SWRLVariable varY = SWRLUtils.createSWRLVariable(varYIRI);
        SWRLVariable varLane = SWRLUtils.createSWRLVariable(varLaneIRI);

        //Class atoms: OWL named class and a single argument representing an OWL individual
        // OnwWayLane(?Lane)
        SWRLClassAtom OneWayLane_Lane = SWRLUtils.createSWRLClassAtom(Map.getInstance().getClassOneWayLane(), varLane);
        // SpeedLimit(?Y)
        SWRLClassAtom SpeedLimit_Y = SWRLUtils.createSWRLClassAtom(Map.getInstance().getClassSpeedLimit(), varY);

        //Property Atoms: OWL object property and two arguments representing OWL individuals
        // isRunningOn(?X, ?Lane)
        SWRLObjectPropertyAtom isRunningOn_X_Lane = SWRLUtils.createSWRLObjectPropertyAtom(MyCar.getInstance().getIsRunningOn(), varX, varLane);
        // overSpeedWarningThan(?X, ?Y)
        SWRLObjectPropertyAtom overSpeedWarningThan_X_Y = SWRLUtils.createSWRLObjectPropertyAtom(SegmentControl.getInstance().getOverSpeedWarningThan(), varX, varY);

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
        SWRLClassAtom head = (speedWarning) ? SWRLUtils.createSWRLClassAtom(SpeedProfile.getInstance().getConstantSpeed(), varX) : SWRLUtils.createSWRLClassAtom(SpeedProfile.getInstance().getAcceleration(), varX);

        // rule creation
        SWRLRule rule = SWRLUtils.createAnonymousSWRLRule(body, Collections.singleton(head));
        ontology.addAxiom(rule);
    }

    /** Created SWRL rule for run direction */
    public static void createRunDirectionSWRLRule(OWLOntologyUtils ontology) {
        SWRLVariable varX = SWRLUtils.createSWRLVariable(varXIRI);
        SWRLVariable varLane = SWRLUtils.createSWRLVariable(varLaneIRI);
        SWRLVariable varNextLane = SWRLUtils.createSWRLVariable(varNextLaneIRI);

        // isRunningOn(?X, ?Lane)
        SWRLObjectPropertyAtom isRunningOn_X_Lane = SWRLUtils.createSWRLObjectPropertyAtom(MyCar.getInstance().getIsRunningOn(), varX, varLane);

        // goStraightTo(?Lane, ?NextLane)
        SWRLObjectPropertyAtom goStraightTo_Lane_NextLane = SWRLUtils.createSWRLObjectPropertyAtom(Map.getInstance().getGoStraightTo(), varLane, varNextLane);
        // GoForward(?X)
        SWRLClassAtom GoForward_X = SWRLUtils.createSWRLClassAtom(SegmentControl.getInstance().getGoForward(), varX);

        // nextPathSegment(?Lane, ?NextLane)
        SWRLObjectPropertyAtom nextPathSegment_Lane_NextLane = SWRLUtils.createSWRLObjectPropertyAtom(SegmentControl.getInstance().getNextPathSegment(), varLane, varNextLane);

        // isRunningOn(?X, ?Lane) ^ goStraightTo(?lane, ?NextLane) ^ nextPathSegment(?Lane, ?NextLane)
        Set<SWRLAtom> body = new HashSet<>();
        body.add(isRunningOn_X_Lane);
        body.add(goStraightTo_Lane_NextLane);
        body.add(nextPathSegment_Lane_NextLane);
        // isRunningOn(?X, ?Lane) ^ goStraightTo(?Lane, ?NextLane) ^ nextPathSegment(?Lane, ?NextLane) -> GoForward(?X)
        SWRLRule rule = SWRLUtils.createAnonymousSWRLRule(
                body,
                Collections.singleton(GoForward_X)
        );
        ontology.addAxiom(rule);
    }
}
