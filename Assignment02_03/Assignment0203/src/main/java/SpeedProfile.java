import org.semanticweb.owlapi.model.OWLClass;

public class SpeedProfile {
    // Classi di SpeedProfile
    private OWLClass acceleration;
    private OWLClass constantSpeed;
    private OWLClass deceleration;

    private static SpeedProfile speedProfileOnto;

    private static SpeedProfile getInstance() {
        if(speedProfileOnto == null) {
            speedProfileOnto = new SpeedProfile();
        }
        return speedProfileOnto;
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