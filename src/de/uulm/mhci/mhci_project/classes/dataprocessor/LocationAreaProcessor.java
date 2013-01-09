package de.uulm.mhci.mhci_project.classes.dataprocessor;


import java.util.Vector;

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
	
	public Vector<Tuple<MapLocation,MetaData>> getLocationsFromPoint(int x,int y,MapLocation[] locs){
		final int MAXDIST=20;
		
		Vector<Tuple<MapLocation,MetaData>> ret = new Vector<Tuple<MapLocation,MetaData>>();
		
		for (MapLocation l:locs){
			int dist = (int) Math.sqrt( Math.pow(l.getXpos()-x,2) + Math.pow(l.getYpos()-y,2) );
			if (dist<MAXDIST)
				ret.add(new Tuple<MapLocation, MetaData>(l, metaProcessor.getMetaDataFromId(l.getId())));
		}
		
		return ret;
	}
	
	public Vector<MapLocation> queryLocation(String category){
		return locProvider.queryLocations(category);
	}
	
	
}
