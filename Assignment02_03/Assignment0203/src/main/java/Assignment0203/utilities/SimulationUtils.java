package Assignment0203.utilities;

import Assignment0203.Control;
import Assignment0203.Map;
import Assignment0203.MyCar;
import Assignment0203.SpeedProfile;
import org.semanticweb.owlapi.model.*;
import java.util.*;

import static Assignment0203.utilities.IRIs.*;

public class SimulationUtils {

    /** Create a simulation to test compliance with speed limits */
    public static OWLOntologyUtils createSpeedLimitSimulation(boolean speedWarning) {
        OWLOntologyUtils myOnto = SimulationUtils.createMyControlDataOntology();
        SimulationUtils.createMyCarRoute(myOnto);
        SimulationUtils.createSpeedLimitSWRLRule(myOnto, speedWarning);
        myOnto.addAxiom(OntologyUtils.createObjectPropertyAssertionAxiom(
                MyCar.getInstance().getMyCarIndividual(),
                Control.getInstance().getOverSpeedWarningThan(),
                Map.getInstance().getSpeedLimit()
        ));
        return myOnto;
    }

    /** Creates a new ontology */
    private static OWLOntologyUtils createMyControlDataOntology() {
        //creation of new empty ontology
        OWLOntologyUtils onto = OntologyUtils.newEmptyOntology(myOntologyIRI).get();
        OntologyUtils.setOntology(onto);

        //import remote ontologies
        onto.importOntology(carOntoIRI);
        onto.importOntology(controlOntoIRI);
        onto.importOntology(mapOntoIRI);

        //creation of road segment with 2 lanes
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

    /** Creates new route */
    private static void createMyCarRoute(OWLOntologyUtils ontology) {
        List<OWLNamedIndividual> routeSequence = new ArrayList<>();
        routeSequence.add(Map.getInstance().getRoadSegment().getLaneRight());
        routeSequence.add(Map.getInstance().getRoadSegment().getLaneRight());
        ControlUtils.createPath(ontology, routeSequence);
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
        SWRLObjectPropertyAtom overSpeedWarningThan_X_Y = SWRLUtils.createSWRLObjectPropertyAtom(Control.getInstance().getOverSpeedWarningThan(), varX, varY);

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
}
