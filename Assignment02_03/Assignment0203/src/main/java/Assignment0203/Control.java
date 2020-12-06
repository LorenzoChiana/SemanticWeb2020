package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class Control {
    // DrivingAction
    private OWLClass go;
    private OWLClass stop;
    private OWLClass goForward;
    private OWLClass turnLeft;
    private OWLClass turnRight;

    // Path > PathSegment > LaneSegment
    private OWLClass startLane;
    private OWLClass endLane;

    // TrafficSignalControl
    private OWLClass greenGo;
    private OWLClass redStop;
    private OWLClass yellow;

    // Warnings
    private OWLClass overSpeedWarning;

    // Properties
    private OWLObjectProperty overSpeedWarningThan;
    private OWLObjectProperty nextPathSegment;
    private OWLDataProperty pathSegmentID;

    private static Control myControlOnto;

    public static Control getInstance() {
        if(myControlOnto == null) {
            myControlOnto = new Control();
        }
        return myControlOnto;
    }

    public OWLClass getGo() {
        return go;
    }

    public void setGo(OWLClass go) {
        this.go = go;
    }

    public OWLClass getStop() {
        return stop;
    }

    public void setStop(OWLClass stop) {
        this.stop = stop;
    }

    public OWLClass getGoForward() {
        return goForward;
    }

    public void setGoForward(OWLClass goForward) {
        this.goForward = goForward;
    }

    public OWLClass getTurnLeft() {
        return turnLeft;
    }

    public void setTurnLeft(OWLClass turnLeft) {
        this.turnLeft = turnLeft;
    }

    public OWLClass getTurnRight() {
        return turnRight;
    }

    public void setTurnRight(OWLClass turnRight) {
        this.turnRight = turnRight;
    }

    public OWLClass getStartLane() {
        return startLane;
    }

    public void setStartLane(OWLClass startLane) {
        this.startLane = startLane;
    }

    public OWLClass getEndLane() {
        return endLane;
    }

    public void setEndLane(OWLClass endLane) {
        this.endLane = endLane;
    }

    public OWLClass getGreenGo() {
        return greenGo;
    }

    public void setGreenGo(OWLClass greenGo) {
        this.greenGo = greenGo;
    }

    public OWLClass getRedStop() {
        return redStop;
    }

    public void setRedStop(OWLClass redStop) {
        this.redStop = redStop;
    }

    public OWLClass getYellow() {
        return yellow;
    }

    public void setYellow(OWLClass yellow) {
        this.yellow = yellow;
    }

    public OWLClass getOverSpeedWarning() {
        return overSpeedWarning;
    }

    public void setOverSpeedWarning(OWLClass overSpeedWarning) {
        this.overSpeedWarning = overSpeedWarning;
    }

    public OWLObjectProperty getOverSpeedWarningThan() {
        return overSpeedWarningThan;
    }

    public void setOverSpeedWarningThan(OWLObjectProperty overSpeedWarningThan) {
        this.overSpeedWarningThan = overSpeedWarningThan;
    }

    public OWLObjectProperty getNextPathSegment() {
        return nextPathSegment;
    }

    public void setNextPathSegment(OWLObjectProperty nextPathSegment) {
        this.nextPathSegment = nextPathSegment;
    }

    public OWLDataProperty getPathSegmentID() {
        return pathSegmentID;
    }

    public void setPathSegmentID(OWLDataProperty pathSegmentID) {
        this.pathSegmentID = pathSegmentID;
    }
}
