package Assignment0203;

import org.semanticweb.owlapi.model.OWLClass;

public class SpeedProfile {
    private OWLClass acceleration;
    private OWLClass constantSpeed;
    private OWLClass deceleration;

    private static SpeedProfile speedProfileOnto;

    public static SpeedProfile getInstance() {
        return (speedProfileOnto == null) ? speedProfileOnto = new SpeedProfile() : speedProfileOnto;
    }

    public OWLClass getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(OWLClass acceleration) {
        this.acceleration = acceleration;
    }

    public OWLClass getConstantSpeed() {
        return constantSpeed;
    }

    public void setConstantSpeed(OWLClass constantSpeed) {
        this.constantSpeed = constantSpeed;
    }

    public OWLClass getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(OWLClass deceleration) {
        this.deceleration = deceleration;
    }
}
