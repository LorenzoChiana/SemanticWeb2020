package Assignment0203;

import Assignment0203.utilities.OWLOntologyUtils;
import Assignment0203.utilities.ReasonerUtils;
import Assignment0203.utilities.SimulationUtils;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

public class Main {
    public static void main(String[] args) {
        OWLOntologyUtils myOnto = SimulationUtils.createSpeedLimitSimulation(true);

        System.out.println("My ontology infos:");
        System.out.println("    - Number of axioms: " + myOnto.getOntology().getAxiomCount());
        System.out.println("    - Number of classes: " + myOnto.getOntology().classesInSignature().count());
        System.out.println("    - Number of individuals: " + myOnto.getOntology().individualsInSignature().count());
        System.out.println("    - Number of object properties: " + myOnto.getOntology().objectPropertiesInSignature().count());
        System.out.println("    - Number of data properties: " + myOnto.getOntology().dataPropertiesInSignature().count());
        System.out.println("    - Number of logical axioms: " + myOnto.getOntology().logicalAxioms().count());

        OWLReasoner reasoner = ReasonerUtils.newReasoner(myOnto.getOntology());
        if(!reasoner.isConsistent()) {
            System.out.println("Error: ontology is not consistent.");
        } else {
            System.out.println("Ontology is consistent.");
        }

        reasoner.dispose();

        //myOnto.printOntology();
    }
}
