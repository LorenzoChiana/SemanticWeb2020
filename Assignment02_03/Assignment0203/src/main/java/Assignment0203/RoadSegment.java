package Assignment0203;

import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class RoadSegment {
    private OWLNamedIndividual roadSegment;
    private OWLNamedIndividual carriagewayR;
    private OWLNamedIndividual carriagewayL;

    public RoadSegment(OWLNamedIndividual road,
                       OWLNamedIndividual carriagewayR,
                       OWLNamedIndividual carriagewayL) {
        this.roadSegment = road;
        this.carriagewayR = carriagewayR;
        this.carriagewayL = carriagewayL;
    }

    public OWLNamedIndividual getRoadSegment() {
        return this.roadSegment;
    }

    public OWLNamedIndividual getCarriagewayR() {
        return this.carriagewayR;
    }

    public OWLNamedIndividual getCarriagewayL() {
        return this.carriagewayL;
    }
}
