package de.uulm.mhci.mhci_project.classes.dataprocessor;


import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.dataprovider.LocationDataProvider;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;


public class LocationAreaProcessor {
	public static final int MAXDIST=70;
	private final MetaDataProcessor metaProcessor;
	private LocationDataProvider locProvider;

	
	public LocationAreaProcessor() {
		
		metaProcessor = new MetaDataProcessor();
		locProvider = new LocationDataProvider();
		
	}
	
	
	
	public Vector<Tuple<MapLocation,MetaData>> getLocationsFromPoint(int x,int y,Vector<MapLocation> locs){
		int mindist=MAXDIST+10;
		
		Vector<Tuple<MapLocation,MetaData>> ret = new Vector<Tuple<MapLocation,MetaData>>();
		
		Tuple<MapLocation,MetaData> minDistEntry=null;
		
		int minDistPos=0;
		
		int i=0;
		for (MapLocation l:locs){
			int dist = (int) Math.sqrt( Math.pow(l.getXpos()-x,2) + Math.pow(l.getYpos()-y,2) );
			
			if (dist<MAXDIST){
				Tuple<MapLocation,MetaData> t = new Tuple<MapLocation, MetaData>(l, metaProcessor.getMetaDataFromId(l.getId()));
				ret.add(t);
				if (dist<mindist){
					mindist=dist;
					minDistEntry=t;
					minDistPos=i;
					
				}
				i++;
			}
			
				
		}
		
		if (ret.size()>1){
			
			ret.set(minDistPos, ret.get(0));
			ret.set(0,minDistEntry);	
		}
		
		
		
		return ret;
	}
	
	public Vector<MapLocation> getLocations(){
		return locProvider.getLocations();
	}
	
	
}
