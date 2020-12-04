package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class MyCar {
    private OWLClass myCar;
    private OWLObjectProperty isRunningOn;
    private OWLDataProperty carId;

    private static MyCar myCarOnto;

    public static MyCar getInstance() {
        if(myCarOnto == null) {
            myCarOnto = new MyCar();
        }
        return myCarOnto;
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
}
