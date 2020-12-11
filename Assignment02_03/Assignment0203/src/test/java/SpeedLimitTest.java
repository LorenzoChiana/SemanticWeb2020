import Assignment0203.MyCar;
import Assignment0203.SpeedProfile;
import Assignment0203.utilities.OWLOntologyUtils;
import Assignment0203.utilities.ReasonerUtils;
import Assignment0203.utilities.SimulationUtils;
import org.junit.Test;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SpeedLimitTest {
    @Test
    public void test1() {
        OWLOntologyUtils onto = SimulationUtils.createSpeedLimitSimulation(false);
        OWLReasoner reasoner = ReasonerUtils.newReasoner(onto.getOntology());

        assertTrue("MyCar must run on only one segment",
                ReasonerUtils.getMyCarPosition(reasoner).nodes().count() == 1);

        assertFalse("MyCar must run with a constant speed.",
                reasoner.getInstances(SpeedProfile.getInstance().getConstantSpeed()).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        assertTrue("MyCar must run with an accelerate speed.",
                reasoner.getInstances(SpeedProfile.getInstance().getAcceleration()).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        reasoner.dispose();
    }

    @Test
    public void test2() {
        OWLOntologyUtils onto = SimulationUtils.createSpeedLimitSimulation(true);
        OWLReasoner reasoner = ReasonerUtils.newReasoner(onto.getOntology());

        assertTrue("MyCar must run on only one segment",
                ReasonerUtils.getMyCarPosition(reasoner).nodes().count() == 1);

        assertTrue("MyCar must run with a constant speed.",
                reasoner.getInstances(SpeedProfile.getInstance().getConstantSpeed()).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        assertFalse("MyCar must run with an accelerate speed.",
                reasoner.getInstances(SpeedProfile.getInstance().getAcceleration()).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        reasoner.dispose();
    }
}
