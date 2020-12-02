package utilities;

import org.semanticweb.owlapi.model.IRI;

public class IRIs {
    private final static String myOntologyString = "http://prova/myontology";
    public final static IRI myOntologyIRI = IRI.create(myOntologyString);
    // CarOnto, ControlOnto and MapOnto iri
    private final static String carOntoString = "http://ri-www.nii.ac.jp/ADAS/CarOnto.owl";
    private final static String controlOntoString = "http://ri-www.nii.ac.jp/ADAS/ControlOnto.owl";
    private final static String mapOntoString = "http://ri-www.nii.ac.jp/ADAS/MapOnto.owl";
    public final static IRI carOntoIRI = IRI.create(carOntoString);
    public final static IRI controlOntoIRI = IRI.create(controlOntoString);
    public final static IRI mapOntoIRI = IRI.create(mapOntoString);


}
