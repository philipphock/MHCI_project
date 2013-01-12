package de.uulm.mhci.mhci_project.model;

import java.util.Vector;

import android.util.Log;

import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;

public class SelectionSurfaceModel {

	
	private final LocationAreaProcessor lap;
	private Vector<MapLocation> mapLocs;
	
	private Vector<Tuple<MapLocation,MetaData>> selection; 
	
	private volatile int touch_x=-1;
	private volatile int touch_y=-1;
	private volatile int activeLocationId=-1;
	
	public SelectionSurfaceModel() {
				
		this.lap = new LocationAreaProcessor();
		
		mapLocs=lap.getLocations();
	}
	
	public Vector<Tuple<MapLocation,MetaData>> getSelection(){
		return selection;
	}
	

	public int getTouch_x() {
		return touch_x;
	}

	public void setTouch_x(int touch_x) {
		this.touch_x = touch_x;
	}

	public int getTouch_y() {
		return touch_y;
	}

	public void setTouch_y(int touch_y) {
		this.touch_y = touch_y;
	}
	
	public Vector<MapLocation> getMapLocations(){
		return this.mapLocs;
	}



	public int getActiveLocationId() {
		return activeLocationId;
	}



	public void setActiveLocationId(int activeLocationId) {
		this.activeLocationId = activeLocationId;
	}
	
	/**
	 * a callback called by the TouchHandler on click
	 * 
	 * @param x pixel
	 * @param y pixel
	 * @param x_rel 0..1
	 * @param y_rel 0..1
	 * 
	 */
	public void click(int x, int y,double x_rel, double y_rel){
		
		
		Vector<Tuple<MapLocation,MetaData>> res = lap.getLocationsFromPoint(x, y, mapLocs);
		
		for (Tuple<MapLocation,MetaData> t: res){
			Log.d("klick",String.format("Name: %s Category: %s", t.a.getName(),t.b.getCategory()));
			
		}
		
		if (res.size()>1){
			touch_x = x;
			touch_y = y;
			activeLocationId=res.get(0).a.getId();
		}else if (res.size()==1){
			activeLocationId=res.get(0).a.getId();
			touch_x = -1;
			touch_y = -1;
		}else{
			activeLocationId=-1;
			touch_x = -1;
			touch_y = -1;
		}
		selection = lap.getLocationsFromPoint(x, y, mapLocs);
	}
	
	
	
}
