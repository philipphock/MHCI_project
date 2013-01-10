package de.uulm.mhci.mhci_project.classes.dataprovider;


import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class LocationDataProvider {

	private Vector<MapLocation> mapLocation;
	
	public LocationDataProvider() {
		mapLocation = new Vector<MapLocation>();
		createNewValues();
	}
	
	private void createNewValues(){
		mapLocation.clear();

		mapLocation.add(new MapLocation(0,"Mc Donalds",0,0));
		
		mapLocation.add(new MapLocation(1,"BurgerKing",0.5,0.5));
		mapLocation.add(new MapLocation(2,"KFC",1,1));
		mapLocation.add(new MapLocation(3,"Subway",0,1));
		mapLocation.add(new MapLocation(4,"Hooters",1,0));
		
		mapLocation.add(new MapLocation(5,"Happy Hair",0.05,0.05));
		
		mapLocation.add(new MapLocation(6,"Trödler",0.55,0.55));
		
		
		
		
	}
	
	public Vector<MapLocation> getLocations(){
		return mapLocation;
	}
}
