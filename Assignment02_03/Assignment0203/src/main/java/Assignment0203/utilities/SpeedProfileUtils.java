package Assignment0203.utilities;

import Assignment0203.SpeedProfile;

import static Assignment0203.utilities.IRIs.*;

public class SpeedProfileUtils {
    private final static SpeedProfile speedProfile = SpeedProfile.getInstance();

    /** Creates classes to monitor the speed */
    public static void addSpeedProfile() {
        speedProfile.setConstantSpeed(OntologyUtils.createClass(constantSpeedIRI));
        speedProfile.setAcceleration(OntologyUtils.createClass(accelerationIRI));
        speedProfile.setDeceleration(OntologyUtils.createClass(decelerationIRI));
    }
}
