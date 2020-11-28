import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class Road {
    private OWLNamedIndividual road;
    private OWLNamedIndividual carriagewayR;
    private OWLNamedIndividual carriagewayL;

    public Road(OWLNamedIndividual road,
                OWLNamedIndividual carriagewayR,
                OWLNamedIndividual carriagewayL) {
        this.road = road;
        this.carriagewayR = carriagewayR;
        this.carriagewayL = carriagewayL;
    }

    public OWLNamedIndividual getRoad() {
        return this.road;
    }

    public OWLNamedIndividual getCarriagewayR() {
        return this.carriagewayR;
    }

    public OWLNamedIndividual getCarriagewayL() {
        return this.carriagewayL;
    }
}
