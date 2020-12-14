package Assignment0203.utilities;

import Assignment0203.SegmentControl;
import Assignment0203.MyCar;
import Assignment0203.SpeedProfile;
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

    /** Gets the start node
     *
     * @param reasoner the reasoner instance
     * @return the start node
     */
    public static NodeSet<OWLNamedIndividual> getStartNode(OWLReasoner reasoner) {
        return reasoner.getInstances(SegmentControl.getInstance().getStartLane());
    }

    /** Gets the end node
     *
     * @param reasoner the reasoner instance
     * @return the end node
     */
    public static NodeSet<OWLNamedIndividual> getEndNode(OWLReasoner reasoner) {
        return reasoner.getInstances(SegmentControl.getInstance().getEndLane());
    }

    /** Gets the go forward nodeset
     *
     * @param reasoner the reasoner instance
     * @return the goforward nodeset
     */
    public static NodeSet<OWLNamedIndividual> getGoForward(OWLReasoner reasoner) {
        return reasoner.getInstances(SegmentControl.getInstance().getGoForward());
    }

    /** Gets the acceleration nodeset
     *
     * @param reasoner the reasoner instance
     * @return the acceleration nodeset
     */
    public static NodeSet<OWLNamedIndividual> getAcceleration(OWLReasoner reasoner) {
        return reasoner.getInstances(SpeedProfile.getInstance().getAcceleration());
    }

    /** Gets the constant speed nodeset
     *
     * @param reasoner the reasoner instance
     * @return the constant speed nodeset
     */
    public static NodeSet<OWLNamedIndividual> getConstantSpeed(OWLReasoner reasoner) {
        return reasoner.getInstances(SpeedProfile.getInstance().getConstantSpeed());
    }
}
