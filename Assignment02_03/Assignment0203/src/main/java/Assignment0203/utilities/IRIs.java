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
    public final static IRI roadSegmentIRI = IRI.create(mapOntoString + "#RoadSegment");
    public final static IRI oneWayLaneIRI = IRI.create(mapOntoString + "#OneWayLane");
    public final static IRI speedLimitIRI = IRI.create((mapOntoString + "#SpeedLimit"));
    //Classes of ControlOnto
    public final static IRI overSpeedWarningIRI = IRI.create(myOntologyString + "#OverSpeedWarning");
    public final static IRI startNodeIRI = IRI.create(controlOntoIRI + "#StartNode");
    public final static IRI endNodeIRI = IRI.create(controlOntoIRI + "#EndNode");
    //Classes of CarOnto
    public final static IRI myCarIRI = IRI.create(carOntoString + "#MyCar");
    public final static IRI accelerationIRI = IRI.create(carOntoString + "#Acceleration");
    public final static IRI constantSpeedIRI = IRI.create(carOntoString + "#ConstantSpeed");
    public final static IRI decelerationIRI = IRI.create(carOntoString + "#Deceleration");
    //Individuals
    public final static IRI individualRoadSegment = IRI.create(myOntologyString + "#individualRoadSegment");
    public final static IRI individualRoadSegmentCarriagewayR = IRI.create(myOntologyString + "#individualRoadSegmentCarriagewayR");
    public final static IRI individualRoadSegmentCarriagewayL = IRI.create(myOntologyString + "#individualRoadSegmentCarriagewayL");
    public final static IRI individualMyCarIRI = IRI.create(myOntologyString + "#lorenzoCar");
    public final static IRI individualSpeedLimit = IRI.create(myOntologyString + "#speedLimit");
    //Properties
    public final static IRI hasLaneIRI = IRI.create(mapOntoString + "#hasLane");
    public final static IRI isLaneOfIRI = IRI.create(mapOntoString + "#isLaneOf");
    public final static IRI overSpeedWarningThanIRI = IRI.create(myOntologyString + "#overSpeedWarningThan");
    public final static IRI isRunningOnIRI = IRI.create(carOntoString + "#isRunningOn");
    public final static IRI carIdIRI = IRI.create(carOntoString + "#carID");
    public final static IRI speedMaxIRI = IRI.create(mapOntoString + "#speedMax");
    public final static IRI nextPathSegmentIRI = IRI.create(controlOntoString + "#nextPathSegment");
    public final static IRI pathSegmentIDIRI = IRI.create(controlOntoString + "#pathSegmentID");


}
