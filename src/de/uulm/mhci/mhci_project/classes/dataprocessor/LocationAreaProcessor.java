package de.uulm.mhci.mhci_project.classes.dataprocessor;


import de.uulm.mhci.mhci_project.classes.dataprovider.LocationDataProvider;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;


public class LocationAreaProcessor {

	private final MetaDataProcessor metaProcessor;
	private LocationDataProvider locProvider;

	public LocationAreaProcessor() {
		
		metaProcessor = new MetaDataProcessor();
		locProvider = new LocationDataProvider();
		
	}
	
	public Tuple<MapLocation,MetaData> getLocationsFromPoint(double x, double y,MapLocation[] locs){
		MapLocation r0=null;
		MetaData r1=null;
		//TODO
		return new Tuple<MapLocation, MetaData>(r0, r1);
	}
	
	//public MapLocation[] queryLocation(String category)
	
}
