package Assignment0203.utilities;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import java.util.Optional;

public class OntologyUtils {
    private static OWLOntologyUtils currentOntology;

    public static void setOntology(OWLOntologyUtils ontology) {
        currentOntology = ontology;
    }

    public static OWLOntologyUtils getOntology() {
        return currentOntology;
    }

    /*
        Methods for creation: references to http://owlapi.sourceforge.net/documentation.html
     */

    public static Optional<OWLOntologyUtils> newEmptyOntology(IRI ontologyIRI) {
        try {
            return Optional.of(new OWLOntologyUtils(OWLManager.createOWLOntologyManager().createOntology(ontologyIRI)));
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static OWLNamedIndividual createIndividual(OWLOntologyUtils ontology, IRI individualIRI) {
       return ontology.getDataFactory().getOWLNamedIndividual(individualIRI);
    }

    public static OWLObjectProperty createObjectProperty(OWLOntologyUtils ontology, IRI objectPropertyIRI) {
        return ontology.getDataFactory().getOWLObjectProperty(objectPropertyIRI);
    }

    public static OWLObjectProperty createObjectProperty(IRI objectPropertyIRI) {
        return createObjectProperty(getOntology(), objectPropertyIRI);
    }

    public static OWLObjectPropertyAssertionAxiom createObjectPropertyAssertionAxiom(OWLOntologyUtils ontology, OWLIndividual subject, OWLObjectProperty property, OWLIndividual object) {
        return ontology.getDataFactory().getOWLObjectPropertyAssertionAxiom(property, subject, object);
    }

    public static OWLObjectPropertyAssertionAxiom createObjectPropertyAssertionAxiom(OWLIndividual subject, OWLObjectProperty property, OWLIndividual object) {
        return createObjectPropertyAssertionAxiom(getOntology(), subject, property, object);
    }

    public static OWLInverseObjectPropertiesAxiom createInverseObjectProperty(OWLOntologyUtils ontology, OWLObjectProperty forwardProperty, OWLObjectProperty inverseProperty) {
        return ontology.getDataFactory().getOWLInverseObjectPropertiesAxiom(forwardProperty, inverseProperty);
    }

    public static OWLInverseObjectPropertiesAxiom createInverseObjectProperty(OWLObjectProperty forwardProperty, OWLObjectProperty inverseProperty) {
        return createInverseObjectProperty(getOntology(), forwardProperty, inverseProperty);
    }

    public static OWLClass createClass(OWLOntologyUtils ontology, IRI classIRI) {
        return ontology.getDataFactory().getOWLClass(classIRI);
    }

    public static OWLClass createClass(IRI classIRI) {
        return createClass(getOntology(), classIRI);
    }

    public static OWLClassAssertionAxiom createClassAssertionAxiom(OWLOntologyUtils ontology, OWLClass owlClass, OWLIndividual individual) {
        return ontology.getDataFactory().getOWLClassAssertionAxiom(owlClass, individual);
    }

    public static OWLClassAssertionAxiom createClassAssertionAxiom(OWLClass owlClass, OWLIndividual individual) {
        return createClassAssertionAxiom(getOntology(), owlClass, individual);
    }

    public static OWLNamedIndividual createIndividualAndSetHisType(OWLOntologyUtils ontology, IRI individualIri, OWLClass individualType) {
        OWLNamedIndividual individual = OntologyUtils.createIndividual(ontology, individualIri);
        ontology.addAxiom(OntologyUtils.createClassAssertionAxiom(ontology, individualType, individual));
        return individual;
    }

    public static OWLNamedIndividual createIndividualAndSetHisType(IRI individualIri, OWLClass individualType) {
        return createIndividualAndSetHisType(getOntology(), individualIri, individualType);
    }

    public static OWLDataProperty createDataProperty(OWLOntologyUtils ontology, IRI dataPropertyIRI) {
        return ontology.getDataFactory().getOWLDataProperty(dataPropertyIRI);
    }

    public static OWLDataProperty createDataProperty(IRI dataPropertyIRI) {
        return createDataProperty(getOntology(), dataPropertyIRI);
    }

    public static OWLDataPropertyAssertionAxiom createDataPropertyAxiom(OWLOntologyUtils ontology, OWLDataProperty dataProperty, OWLIndividual individual, String value) {
        return ontology.getDataFactory().getOWLDataPropertyAssertionAxiom(dataProperty, individual, value);
    }

    public static OWLDataPropertyAssertionAxiom createDataPropertyAxiom(OWLDataProperty dataProperty, OWLIndividual individual, String value) {
        return createDataPropertyAxiom(getOntology(), dataProperty, individual, value);
    }

}
