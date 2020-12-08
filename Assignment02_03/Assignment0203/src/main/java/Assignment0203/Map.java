package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class Map {
    //RouteOfTransportation > RoadPart > RoadSegment
    private OWLClass classRoadSegment;
    //RouteOfTransportation > RoadPart > Lane
    private OWLClass classOneWayLane;
    //RouteOfTransportation
    private OWLClass classSpeedLimit;

    private OWLNamedIndividual speedLimit;
    private RoadSegment roadSegment;

    //Object properties
    private OWLObjectProperty hasLane;
    private OWLObjectProperty isConnectedTo;
    private OWLObjectProperty isLaneOf;
    private OWLObjectProperty goStraightTo;

    //Data property
    private OWLDataProperty speedMax;

    private static Map mapOnto = null;

    public static Map getInstance() {
        return (mapOnto == null) ? mapOnto = new Map() : mapOnto;
    }

    public OWLClass getClassRoadSegment() {
        return classRoadSegment;
    }

    public void setClassRoadSegment(OWLClass classRoadSegment) {
        this.classRoadSegment = classRoadSegment;
    }

    public OWLClass getClassOneWayLane() {
        return classOneWayLane;
    }

    public void setClassOneWayLane(OWLClass classOneWayLane) {
        this.classOneWayLane = classOneWayLane;
    }

    public OWLClass getClassSpeedLimit() {
        return classSpeedLimit;
    }

    public void setClassSpeedLimit(OWLClass classSpeedLimit) {
        this.classSpeedLimit = classSpeedLimit;
    }

    public OWLNamedIndividual getSpeedLimit() {
        return speedLimit;
    }

    public void setSpeedLimit(OWLNamedIndividual speedLimit) {
        this.speedLimit = speedLimit;
    }

    public OWLObjectProperty getHasLane() {
        return hasLane;
    }

    public void setHasLane(OWLObjectProperty hasLane) {
        this.hasLane = hasLane;
    }

    public OWLObjectProperty getIsConnectedTo() {
        return isConnectedTo;
    }

    public void setIsConnectedTo(OWLObjectProperty isConnectedTo) {
        this.isConnectedTo = isConnectedTo;
    }

    public OWLObjectProperty getIsLaneOf() {
        return isLaneOf;
    }

    public void setIsLaneOf(OWLObjectProperty isLaneOf) {
        this.isLaneOf = isLaneOf;
    }

    public OWLDataProperty getSpeedMax() {
        return speedMax;
    }

    public void setSpeedMax(OWLDataProperty speedMax) {
        this.speedMax = speedMax;
    }

    public RoadSegment getRoadSegment() {
        return roadSegment;
    }

    public void setRoadSegment(RoadSegment roadSegment) {
        this.roadSegment = roadSegment;
    }

    public OWLObjectProperty getGoStraightTo() {
        return goStraightTo;
    }

    public void setGoStraightTo(OWLObjectProperty goStraightTo) {
        this.goStraightTo = goStraightTo;
    }
}
