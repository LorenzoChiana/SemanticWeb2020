package Assignment0203.utilities;

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

    //Classes of MapOnto
    public final static IRI roadSegmentClassIRI = IRI.create(mapOntoString + "#RoadSegment");
    public final static IRI oneWayLaneClassIRI = IRI.create(mapOntoString + "#OneWayLane");
    //Classes of ControlOnto
    public final static IRI overSpeedWarningClassIRI = IRI.create(myOntologyString + "#OverSpeedWarning");
    //Classes of CarOnto
    public final static IRI myCarIRI = IRI.create(carOntoString + "#MyCar");
    public final static IRI accelerationIRI = IRI.create(carOntoString + "#Acceleration");
    public final static IRI constantSpeedIRI = IRI.create(carOntoString + "#ConstantSpeed");
    public final static IRI decelerationIRI = IRI.create(carOntoString + "#Deceleration");

    //Individuals
    public final static IRI individualRoadSegment = IRI.create(myOntologyString + "#individualRoadSegment");
    public final static IRI individualRoadSegmentCarriagewayR = IRI.create(myOntologyString + "#individualRoadSegmentCarriagewayR");
    public final static IRI individualRoadSegmentCarriagewayL = IRI.create(myOntologyString + "#individualRoadSegmentCarriagewayL");

    //Properties
    public final static IRI hasLaneIRI = IRI.create(mapOntoString + "#hasLane");
    public final static IRI isLaneOfIRI = IRI.create(mapOntoString + "#isLaneOf");
    public final static IRI overSpeedWarningThanIRI = IRI.create(myOntologyString + "#overSpeedWarningThan");
    public final static IRI carIdIRI = IRI.create(carOntoString + "#carID");

}
