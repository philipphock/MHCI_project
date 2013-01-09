package de.uulm.mhci.mhci_project.classes.dataprovider;

import java.util.HashMap;
import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class LocationDataProvider {

	private final HashMap<String, Vector<MapLocation>> mapLocation;
	public LocationDataProvider() {
		 
		mapLocation = new HashMap<String,Vector<MapLocation>>();
		createNewValues();
	}
	
	private void createNewValues(){
		mapLocation.clear();
		Vector<MapLocation> food = new Vector<MapLocation>(5);
		food.add(new MapLocation(0,"Mc Donalds",0,0));
		food.add(new MapLocation(1,"BurgerKing",0.5,0.5));
		food.add(new MapLocation(2,"KFC",1,1));
		food.add(new MapLocation(3,"Subway",0,1));
		food.add(new MapLocation(4,"Hooters",1,0));
		
		mapLocation.put("food", food);
		
		
		
		
	}
	
	public Vector<MapLocation> queryLocations(String category){
		return mapLocation.get(category);
	}
}
