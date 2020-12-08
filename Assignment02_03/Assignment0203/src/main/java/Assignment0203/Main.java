package Assignment0203;

import Assignment0203.utilities.OWLOntologyUtils;
import Assignment0203.utilities.ReasonerUtils;
import Assignment0203.utilities.SimulationUtils;
import org.apache.jena.base.Sys;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import static Assignment0203.utilities.IRIs.*;

public class Main {
    public static void main(String[] args) {
        OWLOntologyUtils myOnto = SimulationUtils.createSpeedLimitSimulation();

        OWLReasoner reasoner = ReasonerUtils.newReasoner(myOnto.getOntology());
        if(!reasoner.isConsistent()) {
            System.out.println("Error: ontology is not consistent.");
        } else {
            System.out.println("Ontology is consistent.");
            System.out.println("From: " + ReasonerUtils.getStartNode(reasoner).toString() + " To: " + ReasonerUtils.getEndNode(reasoner).toString());
            System.out.println("myCar position: " + ReasonerUtils.getMyCarPosition(reasoner).toString());
        }

        reasoner.dispose();
    }
}
