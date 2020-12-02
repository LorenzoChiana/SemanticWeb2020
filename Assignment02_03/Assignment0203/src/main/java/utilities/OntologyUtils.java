package utilities;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.util.Optional;

public class OntologyUtils {
    private static final String ontologyPath = System.getProperty("user.home") + File.separator + "Assignment0203";

    private static OWLOntologyUtil currentOntology;

    public static void setOntology(OWLOntologyUtil ontology) {
        currentOntology = ontology;
    }

    public static OWLOntologyUtil getOntology() {
        return currentOntology;
    }

    /*
        Methods for creation: references to http://owlapi.sourceforge.net/documentation.html
     */

    public static Optional<OWLOntologyUtil> newEmptyOntology(IRI ontologyIRI) {
        try {
            return Optional.of(new OWLOntologyUtil(OWLManager.createOWLOntologyManager().createOntology(ontologyIRI)));
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static OWLNamedIndividual createIndividual(OWLOntologyUtil ontology, IRI individualIRI) {
       return ontology.getDataFactory().getOWLNamedIndividual(individualIRI);
    }

    public static OWLNamedIndividual createIndividual(IRI individualIRI) {
        return createIndividual(getOntology(), individualIRI);
    }

    public static OWLObjectProperty createObjectProperty(OWLOntologyUtil ontology, IRI objectPropertyIRI) {
        return ontology.getDataFactory().getOWLObjectProperty(objectPropertyIRI);
    }

    public static OWLObjectProperty createObjectProperty(IRI objectPropertyIRI) {
        return createObjectProperty(getOntology(), objectPropertyIRI);
    }

    public static OWLSymmetricObjectPropertyAxiom createSymmetricObjectProperty(OWLOntologyUtil ontology, OWLObjectProperty symmetricObjectProperty) {
        return ontology.getDataFactory().getOWLSymmetricObjectPropertyAxiom(symmetricObjectProperty);
    }

    public static OWLSymmetricObjectPropertyAxiom createSymmetricObjectProperty(OWLObjectProperty symmetricObjectProperty) {
        return createSymmetricObjectProperty(getOntology(), symmetricObjectProperty);
    }

    public static OWLObjectPropertyAssertionAxiom createObjectPropertyAssertionAxiom(OWLOntologyUtil ontology, OWLIndividual subject, OWLObjectProperty property, OWLIndividual object) {
        return ontology.getDataFactory().getOWLObjectPropertyAssertionAxiom(property, subject, object);
    }

    public static OWLObjectPropertyAssertionAxiom createObjectPropertyAssertionAxiom(OWLIndividual subject, OWLObjectProperty property, OWLIndividual object) {
        return createObjectPropertyAssertionAxiom(getOntology(), subject, property, object);
    }

    public static OWLInverseObjectPropertiesAxiom createInverseObjectProperty(OWLOntologyUtil ontology, OWLObjectProperty forwardProperty, OWLObjectProperty inverseProperty) {
        return ontology.getDataFactory().getOWLInverseObjectPropertiesAxiom(forwardProperty, inverseProperty);
    }

    public static OWLInverseObjectPropertiesAxiom createInverseObjectProperty(OWLObjectProperty forwardProperty, OWLObjectProperty inverseProperty) {
        return createInverseObjectProperty(getOntology(), forwardProperty, inverseProperty);
    }

    public static OWLClass createClass(OWLOntologyUtil ontology, IRI classIRI) {
        return ontology.getDataFactory().getOWLClass(classIRI);
    }

    public static OWLClass createClass(IRI classIRI) {
        return createClass(getOntology(), classIRI);
    }

    public static OWLClassAssertionAxiom createClassAssertionAxiom(OWLOntologyUtil ontology, OWLClass owlClass, OWLIndividual individual) {
        return ontology.getDataFactory().getOWLClassAssertionAxiom(owlClass, individual);
    }

    public static OWLClassAssertionAxiom createClassAssertionAxiom(OWLClass owlClass, OWLIndividual individual) {
        return createClassAssertionAxiom(getOntology(), owlClass, individual);
    }

    public static OWLDeclarationAxiom createDeclarationAxiom(OWLOntologyUtil ontology, OWLClass owlClass) {
        return createDeclarationAxiom(ontology, (OWLEntity) owlClass);
    }

    public static OWLDeclarationAxiom createDeclarationAxiom(OWLClass owlClass) {
        return createDeclarationAxiom(getOntology(), owlClass);
    }

    public static OWLDeclarationAxiom createDeclarationAxiom(OWLOntologyUtil ontology, OWLNamedIndividual individual) {
        return createDeclarationAxiom(ontology, (OWLEntity) individual);
    }

    public static OWLDeclarationAxiom createDeclarationAxiom(OWLNamedIndividual individual) {
        return createDeclarationAxiom(getOntology(), individual);
    }

    public static OWLDeclarationAxiom createIndividualAndHisDeclarationAxiom(OWLOntologyUtil ontology, IRI individualIRI) {
        return OntologyUtils.createDeclarationAxiom(ontology, createIndividual(ontology, individualIRI));
    }

    public static OWLDeclarationAxiom createIndividualAndHisDeclarationAxiom(IRI individualIRI) {
        return createIndividualAndHisDeclarationAxiom(getOntology(), individualIRI);
    }

    public static OWLDeclarationAxiom createDeclarationAxiom(OWLOntologyUtil ontology, OWLEntity declaredEntity) {
        return ontology.getDataFactory().getOWLDeclarationAxiom(declaredEntity);
    }

    public static OWLDeclarationAxiom createDeclarationAxiom(OWLEntity declaredEntity) {
        return createDeclarationAxiom(getOntology(), declaredEntity);
    }

    public static OWLNamedIndividual createIndividualAndSetHisType(OWLOntologyUtil ontology, IRI individualIri, OWLClass individualType) {
        OWLNamedIndividual individual = OntologyUtils.createIndividual(ontology, individualIri);
        ontology.addAxiom(OntologyUtils.createClassAssertionAxiom(ontology, individualType, individual));
        return individual;
    }

    public static OWLNamedIndividual createIndividualAndSetHisType(IRI individualIri, OWLClass individualType) {
        return createIndividualAndSetHisType(getOntology(), individualIri, individualType);
    }

    public static OWLDataProperty createDataProperty(OWLOntologyUtil ontology, IRI dataPropertyIRI) {
        return ontology.getDataFactory().getOWLDataProperty(dataPropertyIRI);
    }

    public static OWLDataProperty createDataProperty(IRI dataPropertyIRI) {
        return createDataProperty(getOntology(), dataPropertyIRI);
    }

    public static OWLDataPropertyAssertionAxiom createDataPropertyAxiom(OWLOntologyUtil ontology, OWLDataProperty dataProperty, OWLIndividual individual, String value) {
        return ontology.getDataFactory().getOWLDataPropertyAssertionAxiom(dataProperty, individual, value);
    }

    public static OWLDataPropertyAssertionAxiom createDataPropertyAxiom(OWLDataProperty dataProperty, OWLIndividual individual, String value) {
        return createDataPropertyAxiom(getOntology(), dataProperty, individual, value);
    }

}
