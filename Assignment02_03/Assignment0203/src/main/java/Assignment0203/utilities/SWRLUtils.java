package Assignment0203.utilities;

import org.semanticweb.owlapi.model.*;

import java.util.Collection;

import static Assignment0203.utilities.OntologyUtils.getOntology;

// Thanks to https://github.com/owlcs/owlapi/wiki/Documentation
public class SWRLUtils {

    public static SWRLVariable createSWRLVariable(OWLOntologyUtils ontology, IRI rule) {
        return ontology.getDataFactory().getSWRLVariable(rule);
    }

    public static SWRLVariable createSWRLVariable(IRI rule) {
        return createSWRLVariable(getOntology(), rule);
    }

    public static SWRLClassAtom createSWRLClassAtom(OWLOntologyUtils ontology, OWLClassExpression predicate, SWRLIArgument arg) {
        return ontology.getDataFactory().getSWRLClassAtom(predicate, arg);
    }

    public static SWRLClassAtom createSWRLClassAtom(OWLClassExpression predicate, SWRLIArgument arg) {
        return createSWRLClassAtom(getOntology(), predicate, arg);
    }

    public static SWRLObjectPropertyAtom createSWRLObjectPropertyAtom(OWLOntologyUtils ontology, OWLObjectPropertyExpression property, SWRLIArgument arg0, SWRLIArgument arg1) {
        return ontology.getDataFactory().getSWRLObjectPropertyAtom(property, arg0, arg1);
    }

    public static SWRLObjectPropertyAtom createSWRLObjectPropertyAtom(OWLObjectPropertyExpression property, SWRLIArgument arg0, SWRLIArgument arg1) {
        return createSWRLObjectPropertyAtom(getOntology(), property, arg0, arg1);
    }

    public static SWRLRule createAnonymousSWRLRule(OWLOntologyUtils ontology, Collection<?extends SWRLAtom> body, Collection<?extends SWRLAtom> head) {
        return ontology.getDataFactory().getSWRLRule(body, head);
    }

    public static SWRLRule createAnonymousSWRLRule(Collection<? extends SWRLAtom> body, Collection<?extends SWRLAtom> head) {
        return createAnonymousSWRLRule(getOntology(), body, head);
    }
}
