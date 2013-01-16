package de.uulm.mhci.mhci_project.model;

import java.util.Vector;

import android.util.Log;

import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;
import de.uulm.mhci.mhci_project.ui.SelectionSliderSurfaceView;

public class SelectionSurfaceModel {

	
	private final LocationAreaProcessor lap;
	private Vector<MapLocation> mapLocs;
	
	private Vector<Tuple<MapLocation,MetaData>> selection; 
	
	private volatile int touch_x=-1;
	private volatile int touch_y=-1;
	private volatile int activeLocationId=-1;
	
	private int sliderOffsetX=0;
	private int sliderOffsetY=0;
	
	public int getSliderOffsetX() {
		return sliderOffsetX;
	}

	public void alignSliderOffsetsToCenter(){
		//TODO align offsets to center
		MapLocation activeMapLocation=null;
		for (Tuple<MapLocation,MetaData> t: selection){
			t.a.updateSliderPosX(sliderOffsetX);
			if (t.a.getId() == activeLocationId){
				activeMapLocation = t.a;
			}
		}
		sliderOffsetX=0;
		 if (activeMapLocation == null) return;
		 int i = -(activeMapLocation.getSliderPosX());
		 
		 for (Tuple<MapLocation,MetaData> t: selection){
				t.a.updateSliderPosX(i);
				
		}
		//activeMapLocation.
	}
	
	
	
	public void setSliderOffsetX(int sliderOffsetX) {
		this.sliderOffsetX = sliderOffsetX;
		Tuple<MapLocation,MetaData> nearestToCenter=null;
		
		for (Tuple<MapLocation,MetaData> t: selection){
			int locationX = t.a.getSliderPosX() + this.sliderOffsetX;
			if (nearestToCenter == null) nearestToCenter=t;
			
			
			if (Math.abs(locationX) < Math.abs(nearestToCenter.a.getSliderPosX() + this.sliderOffsetX)){
				
				nearestToCenter=t;
			}
			
		}
		this.activeLocationId=nearestToCenter.a.getId();		
	}

	public int getSliderOffsetY() {
		return sliderOffsetY;
	}

	public void setSliderOffsetY(int sliderOffsetY) {
		this.sliderOffsetY = sliderOffsetY;
		
	}

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
		
//		for (Tuple<MapLocation,MetaData> t: res){
//			Log.d("klick",String.format("Name: %s Category: %s", t.a.getName(),t.b.getCategory()));
//			
//		}
		
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
		
		//setting the parameters for the slider
		
		int mlocSize = res.size();
		
		int l=0;
		int r=0;
		int i=0;
		
		for (Tuple<MapLocation,MetaData> t: res){
			t.a.setSliderPosY(5);
			if (this.getActiveLocationId() == t.a.getId()){
				//selected
				
				t.a.setSliderPosX(0);
				
			}else{
				i++;
				
				if (i<=mlocSize/2){
					l--;
					t.a.setSliderPosX(l*(SelectionSliderSurfaceView.ITEM_SIZE+SelectionSliderSurfaceView.ITEM_SPACE));
				}else{
					r++;
					t.a.setSliderPosX(r*(SelectionSliderSurfaceView.ITEM_SIZE+SelectionSliderSurfaceView.ITEM_SPACE));					
				}
				
			}
		}
		
		
		
		selection = lap.getLocationsFromPoint(x, y, mapLocs);
	}
	
	
	
}
