package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class MyCar {
    private OWLClass myCar;
    private OWLObjectProperty isRunningOn;
    private OWLDataProperty carId;
    private OWLNamedIndividual myCarIndividual;

    private static MyCar myCarOnto;

    public static MyCar getInstance() {
        return (myCarOnto == null) ? myCarOnto = new MyCar() : myCarOnto;
    }

    public OWLClass getMyCar() {
        return myCar;
    }

    public void setMyCar(OWLClass myCar) {
        this.myCar = myCar;
    }

    public OWLObjectProperty getIsRunningOn() {
        return isRunningOn;
    }

    public void setIsRunningOn(OWLObjectProperty isRunningOn) {
        this.isRunningOn = isRunningOn;
    }

    public OWLDataProperty getCarId() {
        return carId;
    }

    public void setCarId(OWLDataProperty carId) {
        this.carId = carId;
    }

    public OWLNamedIndividual getMyCarIndividual() {
        return myCarIndividual;
    }

    public void setMyCarIndividual(OWLNamedIndividual myCarIndividual) {
        this.myCarIndividual = myCarIndividual;
    }
}
