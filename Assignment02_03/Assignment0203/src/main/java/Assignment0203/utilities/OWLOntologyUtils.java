package Assignment0203.utilities;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.ChangeApplied;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import java.util.Collections;
import java.util.Optional;

//Thanks to https://github.com/owlcs/owlapi/wiki/Documentation
public class OWLOntologyUtils {
    private final OWLOntology ontology;
    private final OWLOntologyManager owlOntologyManager;
    private final OWLEntityRemover owlEntityRemover;
    private final OWLDataFactory owlDataFactory;

    public OWLOntologyUtils(OWLOntology ontology) {
        this.ontology = ontology;
        this.owlOntologyManager = ontology.getOWLOntologyManager();
        this.owlEntityRemover = new OWLEntityRemover(Collections.singleton(ontology));
        this.owlDataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    }

    // getters of OWLOntologyManager, OWLEntityRemover and OWLDataFactory
    public OWLOntology getOntology() {
        return ontology;
    }
    public OWLDataFactory getDataFactory() {
        return this.owlDataFactory;
    }

    /**
     * @return the IRI of ontology
     */
    public Optional<IRI> getOntologyIRI() {
        return ontology.getOntologyID().getOntologyIRI();
    }

    /** Removes elements from ontology manager
     *
     * @return true if successful
     */
    public boolean removeElements() {
        return owlOntologyManager.applyChanges(owlEntityRemover.getChanges()).equals(ChangeApplied.SUCCESSFULLY);
    }

    /** Imports a new ontology from IRI
     *
     * @param ontologyIRI the IRI of ontology
     */
    public void importOntology(IRI ontologyIRI) {
        owlOntologyManager.applyChange(new AddImport(ontology, owlDataFactory.getOWLImportsDeclaration(ontologyIRI)));
    }

    /** Add a new axiom to ontology manager
     *
     * @param axiom the new axiom to add
     */
    public void addAxiom(OWLAxiom axiom) {
        owlOntologyManager.applyChange(new AddAxiom(ontology, axiom));
    }

    /** Removes an axiom to ontology
     *
     * @param axiom the axiom to remove
     * @return true if successful
     */
    public boolean removeAxiom(OWLAxiom axiom) {
        return ontology.remove(axiom) != null;
    }

    /** Prints all ontology */
    public void printOntology() {
        try {
            this.owlOntologyManager.saveOntology(ontology, System.out);
        } catch (OWLOntologyStorageException e) {
            System.out.println("Error: cannot print ontology");
        }
    }

}
