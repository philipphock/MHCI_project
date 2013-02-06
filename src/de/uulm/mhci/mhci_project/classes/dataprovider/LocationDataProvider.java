package de.uulm.mhci.mhci_project.classes.dataprovider;


import java.util.Vector;

import de.uulm.mhci.mhci_project.MainActivity;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class LocationDataProvider {

	private Vector<MapLocation> mapLocation;
	
	public LocationDataProvider() {
		mapLocation = new Vector<MapLocation>();
		createNewValues();
	}
	
	private void createNewValues(){
		mapLocation.clear();
		
		for(int i = 0; i< MainActivity.AMOUNT_OF_TARGETS; i++){
			switch(i%20){
				case 0:
					mapLocation.add(new MapLocation(i,"Mc Donalds",Math.random(),Math.random())); break;
				case 1:
					mapLocation.add(new MapLocation(i,"BurgerKing",Math.random(),Math.random())); break;
				case 2:
					mapLocation.add(new MapLocation(i,"KFC",Math.random(),Math.random())); break;
				case 3: 
					mapLocation.add(new MapLocation(i,"Subway",Math.random(),Math.random())); break;
				case 4: 
					mapLocation.add(new MapLocation(i,"Hooters",Math.random(),Math.random())); break;
				case 5: 
					mapLocation.add(new MapLocation(i,"Happy Hair",Math.random(),Math.random())); break;
				case 6: 
					mapLocation.add(new MapLocation(i,"Nailstudio Finger and Toe",Math.random(),Math.random())); break;
				case 7: 
					mapLocation.add(new MapLocation(i,"ThaiMassages",Math.random(),Math.random())); break;
				case 8: 
					mapLocation.add(new MapLocation(i,"AsiaTwins",Math.random(),Math.random())); break;
				case 9: 
					mapLocation.add(new MapLocation(i,"Alter Herzog",Math.random(),Math.random())); break;
				case 10: 
					mapLocation.add(new MapLocation(i,"Trödler",Math.random(),Math.random())); break;
				case 11: 
					mapLocation.add(new MapLocation(i,"Barfüßer",Math.random(),Math.random())); break;
				case 12:
					mapLocation.add(new MapLocation(i,"Capos Größenwahn",Math.random(),Math.random())); break;
				case 13: 
					mapLocation.add(new MapLocation(i,"Flair",Math.random(),Math.random())); break;
				case 14: 
					mapLocation.add(new MapLocation(i,"Meyers",Math.random(),Math.random())); break;
				case 15: 
					mapLocation.add(new MapLocation(i,"Peek and Cloppenburg",Math.random(),Math.random())); break;
				case 16: 
					mapLocation.add(new MapLocation(i,"C und A",Math.random(),Math.random())); break;
				case 17: 
					mapLocation.add(new MapLocation(i,"H und M",Math.random(),Math.random())); break;
				case 18: 
					mapLocation.add(new MapLocation(i,"ShoeTown",Math.random(),Math.random())); break;
				case 19:
					mapLocation.add(new MapLocation(i,"NewYorker",Math.random(),Math.random())); break;
			}
		}

			
		
		
//		for (MapLocation l:mapLocation){
//			l.setLat(Math.random()/2);
//			l.setLng(Math.random()/2);
//		}
		
	}
	
	public Vector<MapLocation> getLocations(){
		return mapLocation;
	}
}
