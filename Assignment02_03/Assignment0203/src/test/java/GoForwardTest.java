import Assignment0203.Map;
import Assignment0203.MyCar;
import Assignment0203.PathControl;
import Assignment0203.utilities.OWLOntologyUtils;
import Assignment0203.utilities.ReasonerUtils;
import Assignment0203.utilities.SimulationUtils;
import org.junit.Test;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import static org.junit.Assert.assertTrue;

public class GoForwardTest {

    @Test
    public void test1() {
        OWLOntologyUtils onto = SimulationUtils.createSpeedLimitSimulation(true);
        OWLReasoner reasoner = ReasonerUtils.newReasoner(onto.getOntology());

        assertTrue("myCar must go forward.",
                reasoner.getInstances(PathControl.getInstance().getGoForward()).containsEntity(MyCar.getInstance().getMyCarIndividual()));

        assertTrue("StartNode must be the right lane of segment start",
                ReasonerUtils.getStartNode(reasoner).containsEntity(Map.getInstance().getRoadSegmentStart().getLaneRight()));

        assertTrue("EndNode must be the right lane of segment stop",
                ReasonerUtils.getEndNode(reasoner).containsEntity(Map.getInstance().getRoadSegmentStop().getLaneRight()));

        reasoner.dispose();
    }
}
