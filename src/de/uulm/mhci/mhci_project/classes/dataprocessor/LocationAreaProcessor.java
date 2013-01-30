package de.uulm.mhci.mhci_project.classes.dataprocessor;


import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
	
	
	
	public  Tuple<MapLocation,MetaData>  getLocationsFromPoint(int x,int y,Vector<MapLocation> locs,Vector<Tuple<MapLocation,MetaData>> ret){
		int mindist=MAXDIST+10;
		
		
		
		Tuple<MapLocation,MetaData> minDistEntry=null;
		
		
		
		
		for (MapLocation l:locs){
			int dist = (int) Math.sqrt( Math.pow(l.getXpos()-x,2) + Math.pow(l.getYpos()-y,2) );
			
			if (dist<MAXDIST){
				Tuple<MapLocation,MetaData> t = new Tuple<MapLocation, MetaData>(l, metaProcessor.getMetaDataFromId(l.getId()));
				ret.add(t);
				if (dist<mindist){
					mindist=dist;
					minDistEntry=t;
					
					
				}
				
			}
			
				
		}
		//sort the vector
		Collections.sort(ret, new Comparator<Tuple<MapLocation,MetaData>>() {

			@Override
			public int compare(Tuple<MapLocation, MetaData> lhs,
					Tuple<MapLocation, MetaData> rhs) {
				if (lhs.a.getLat() < rhs.a.getLat()){
					return -1;
				}else if(lhs.a.getLat() > rhs.a.getLat()){
					return 1; 
				}
				return 0;
			}
			
		});
		
//		if (ret.size()>1){
//			
//			ret.set(minDistPos, ret.get(0));
//			ret.set(0,minDistEntry);	
//		}
		
		
		
		return minDistEntry;
	}
	
	public Vector<MapLocation> getLocations(){
		return locProvider.getLocations();
	}
	
	
}
