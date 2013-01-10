package de.uulm.mhci.mhci_project.classes.dataprovider;


import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;

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
		mapLocation.add(new MapLocation(6,"Nailstudio Finger and Toe",0.05,0.05));
		mapLocation.add(new MapLocation(7,"ThaiMassages",0.05,0.05));
		mapLocation.add(new MapLocation(8,"AsiaTwins",0.05,0.05));
		mapLocation.add(new MapLocation(9,"Alter Herzog",0.05,0.05));
		
		mapLocation.add(new MapLocation(10,"Trödler",0.05,0.05));
		mapLocation.add(new MapLocation(11,"Barfüßer",0.05,0.05));
		mapLocation.add(new MapLocation(12,"Capos Größenwahn",0.05,0.05));
		mapLocation.add(new MapLocation(13,"Flair",0.05,0.05));
		mapLocation.add(new MapLocation(14,"Meyers",0.05,0.05));
		
		mapLocation.add(new MapLocation(15,"Peek and Cloppenburg",0.05,0.05));
		mapLocation.add(new MapLocation(16,"C und A",0.05,0.05));
		mapLocation.add(new MapLocation(17,"H und M",0.05,0.05));
		mapLocation.add(new MapLocation(18,"ShoeTown",0.05,0.05));
		mapLocation.add(new MapLocation(19,"NewYorker",0.05,0.05));
		
		for (MapLocation l:mapLocation){
			l.setLat(Math.random());
			l.setLng(Math.random());
		}
		
	}
	
	public Vector<MapLocation> getLocations(){
		return mapLocation;
	}
}
