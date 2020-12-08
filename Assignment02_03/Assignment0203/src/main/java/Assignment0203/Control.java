package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class Control {
    // Path > PathSegment > LaneSegment
    private OWLClass startLane;
    private OWLClass endLane;

    // TrafficSignalControl
    /*private OWLClass greenGo;
    private OWLClass redStop;
    private OWLClass yellow;*/

    // Warnings
    private OWLClass overSpeedWarning;

    // Properties
    private OWLObjectProperty overSpeedWarningThan;
    private OWLObjectProperty nextPathSegment;
    private OWLDataProperty pathSegmentID;

    private static Control controlOnto = null;

    public static Control getInstance() {
        return (controlOnto == null) ? controlOnto = new Control() : controlOnto;
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
