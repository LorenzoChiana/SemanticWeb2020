package Assignment0203;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class RoadSegment {
    private final OWLNamedIndividual road;
    private final OWLNamedIndividual laneRight;
    private final OWLNamedIndividual laneLeft;

    public RoadSegment(OWLNamedIndividual road,
                       OWLNamedIndividual laneRight,
                       OWLNamedIndividual laneLeft) {
        this.road = road;
        this.laneRight = laneRight;
        this.laneLeft = laneLeft;
    }

    public OWLNamedIndividual getRoad() {
        return this.road;
    }

    public OWLNamedIndividual getLaneRight() {
        return this.laneRight;
    }

    public OWLNamedIndividual getLaneLeft() {
        return this.laneLeft;
    }
}
