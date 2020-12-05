package Assignment0203.utilities;

import Assignment0203.Control;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import static Assignment0203.utilities.IRIs.*;

public class ControlUtils {
    private final static Control controlData = Control.getInstance();

    /** Creates the classes for the swrl rules about speed */
    public static void addClassForSpeedSWRLRules() {
        controlData.setOverSpeedWarning(OntologyUtils.createClass(overSpeedWarningClassIRI));
    }
    /** Creates the properties for the swrl rules about speed */
    public static void connectPropertiesForSpeedSWRLRules(){
        controlData.setOverSpeedWarningThan(OntologyUtils.createObjectProperty(overSpeedWarningThanIRI));
    }
}
