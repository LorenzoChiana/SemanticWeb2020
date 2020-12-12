package Assignment0203.utilities;

import Assignment0203.SegmentControl;
import Assignment0203.MyCar;
import org.semanticweb.HermiT.ReasonerFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

//Thanks to https://github.com/luigidr/owlapi-example
public class ReasonerUtils {

    /** Creates a new reasoner
     *
     * @param ontology the ontology instance
     * @return the new reasoner
     */
    public static OWLReasoner newReasoner(OWLOntology ontology) {
        return new ReasonerFactory().createReasoner(ontology);
    }

    /** Gets the position of car
     *
     * @param reasoner the reasoner instance
     * @return the car position
     */
    public static NodeSet<OWLNamedIndividual> getMyCarPosition(OWLReasoner reasoner) {
        return reasoner.getObjectPropertyValues(
                MyCar.getInstance().getMyCarIndividual(),
                MyCar.getInstance().getIsRunningOn()
        );
    }

    /** Gets the start node
     *
     * @param reasoner the reasoner instance
     * @return the start node
     */
    public static NodeSet<OWLNamedIndividual> getStartNode(OWLReasoner reasoner) {
        return reasoner.getInstances(SegmentControl.getInstance().getStartLane());
    }

    public static NodeSet<OWLNamedIndividual> where(OWLReasoner reasoner) {
        return reasoner.getInstances(SegmentControl.getInstance().getGoForward());
    }

    /** Gets the end node
     *
     * @param reasoner the reasoner instance
     * @return the end node
     */
    public static NodeSet<OWLNamedIndividual> getEndNode(OWLReasoner reasoner) {
        return reasoner.getInstances(SegmentControl.getInstance().getEndLane());
    }
}
