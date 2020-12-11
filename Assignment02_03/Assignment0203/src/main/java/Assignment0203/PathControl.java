package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class PathControl {
    // Path > PathSegment > LaneSegment
    private OWLClass startLane;
    private OWLClass endLane;

    // TrafficSignalControl
    /*private OWLClass greenGo;
    private OWLClass redStop;
    private OWLClass yellow;*/

    private OWLClass goForward;

    // Warnings
    private OWLClass overSpeedWarning;

    // Properties
    private OWLObjectProperty overSpeedWarningThan;
    private OWLObjectProperty nextPathSegment;
    private OWLDataProperty pathSegmentID;

    private static PathControl pathControlOnto = null;

    public static PathControl getInstance() {
        return (pathControlOnto == null) ? pathControlOnto = new PathControl() : pathControlOnto;
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

    public OWLClass getGoForward() {
        return goForward;
    }

    public void setGoForward(OWLClass goForward) {
        this.goForward = goForward;
    }
}
