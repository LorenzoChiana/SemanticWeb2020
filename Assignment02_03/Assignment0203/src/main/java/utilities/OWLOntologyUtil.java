package utilities;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.model.parameters.ChangeApplied;
import org.semanticweb.owlapi.search.Searcher;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class OWLOntologyUtil {
    private OWLOntology ontology;
    private OWLOntologyManager owlOntologyManager;
    private OWLEntityRemover owlEntityRemover;
    private OWLDataFactory owlDataFactory;

    public OWLOntologyUtil(OWLOntology ontology) {
        this.ontology = ontology;
        this.owlOntologyManager = ontology.getOWLOntologyManager();
        this.owlEntityRemover = new OWLEntityRemover(Collections.singleton(ontology));
        this.owlDataFactory = ontology.getOWLOntologyManager().getOWLDataFactory();
    }

    // getters of OWLOntologyManager, OWLEntityRemover and OWLDataFactory
    public OWLOntologyManager getOntologyManager() {
        return this.owlOntologyManager;
    }
    public OWLEntityRemover getEntityRemover() {
        return this.owlEntityRemover;
    }
    public OWLDataFactory getDataFactory() {
        return this.owlDataFactory;
    }

    /**
     * @return the number of imported declarations
     */
    public long numImportedOntologies() {
        return ontology.importsDeclarations().count();
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
     * @return true if successful
     */
    public boolean importOntology(IRI ontologyIRI) {
        return owlOntologyManager.applyChange(new AddImport(ontology, owlDataFactory.getOWLImportsDeclaration(ontologyIRI))).equals(ChangeApplied.SUCCESSFULLY);
    }

    /** Add a new axiom to ontology manager
     *
     * @param axiom the new axiom to add
     * @return true if successful
     */
    public boolean addAxiom(OWLAxiom axiom) {
        return owlOntologyManager.applyChange(new AddAxiom(ontology, axiom)).equals(ChangeApplied.SUCCESSFULLY);
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
    public void printlnOntology() {
        try {
            this.owlOntologyManager.saveOntology(ontology, System.out);
        } catch (OWLOntologyStorageException e) {
            e.printStackTrace();
        }
    }
}
