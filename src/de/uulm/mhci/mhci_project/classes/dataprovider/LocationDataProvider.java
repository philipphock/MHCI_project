package de.uulm.mhci.mhci_project.classes.dataprovider;

import java.util.HashMap;

import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class LocationDataProvider {

	private final HashMap<String, MapLocation> mapLocation;
	public LocationDataProvider() {
		mapLocation = new HashMap<String, MapLocation>();
		createNewValues();
	}
	
	private void createNewValues(){
		mapLocation.clear();
		
		mapLocation.put("food", new MapLocation(0,"Mc Donalds",0,0));
		mapLocation.put("food", new MapLocation(1,"BurgerKing",0.5,0.5));
		mapLocation.put("food", new MapLocation(2,"KFC",1,1));
		mapLocation.put("food", new MapLocation(3,"Subway",0,1));
		mapLocation.put("food", new MapLocation(4,"Hooters",1,0));
		
		
		
	}
	
}
