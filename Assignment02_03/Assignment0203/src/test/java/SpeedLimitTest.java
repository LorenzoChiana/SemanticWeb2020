import Assignment0203.MyCar;
import Assignment0203.utilities.OWLOntologyUtils;
import Assignment0203.utilities.ReasonerUtils;
import Assignment0203.utilities.SimulationUtils;
import org.junit.Test;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import static org.junit.Assert.*;

public class SpeedLimitTest {
    @Test
    public void test1() {
        OWLOntologyUtils onto = SimulationUtils.createSimulation(false);
        OWLReasoner reasoner = ReasonerUtils.newReasoner(onto.getOntology());

        assertFalse("MyCar must run with a constant speed.",
                ReasonerUtils.getConstantSpeed(reasoner).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        assertTrue("MyCar must run with an accelerate speed.",
                ReasonerUtils.getAcceleration(reasoner).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        reasoner.dispose();
    }

    @Test
    public void test2() {
        OWLOntologyUtils onto = SimulationUtils.createSimulation(true);
        OWLReasoner reasoner = ReasonerUtils.newReasoner(onto.getOntology());

       assertTrue("MyCar must run with a constant speed.",
               ReasonerUtils.getConstantSpeed(reasoner).containsEntity(MyCar.getInstance().getMyCarIndividual()));

       assertFalse("MyCar must run with an accelerate speed.",
               ReasonerUtils.getAcceleration(reasoner).containsEntity(MyCar.getInstance().getMyCarIndividual()));

       reasoner.dispose();
    }
}
