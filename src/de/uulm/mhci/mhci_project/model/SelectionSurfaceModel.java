package de.uulm.mhci.mhci_project.model;

import java.util.Collections;
import java.util.Vector;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import de.uulm.mhci.mhci_project.MainActivity;
import de.uulm.mhci.mhci_project.R;
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
	
	private int mapOffsetX=0;
	private int mapOffsetY=0;
	
	private int zoomOffsetX=0;
	private int zoomOffsetY=0;
	
	private float deltaZ = 0 ;
	
	public float getDeltaZ() {
		return deltaZ;
	}

	public void setDeltaZ(float deltaZ) {
		this.deltaZ = deltaZ;
	}

	public volatile boolean noSelection=false;
	
	private float zoomLevel=1.0f;
	
	
	public int getZoomOffsetX() {
		return zoomOffsetX;
	}

	public void setZoomOffsetX(int zoomOffsetX) {
		this.zoomOffsetX = zoomOffsetX;
	}

	public int getZoomOffsetY() {
		return zoomOffsetY;
	}

	public void setZoomOffsetY(int zoomOffsetY) {
		this.zoomOffsetY = zoomOffsetY;
	}

	public int getMapOffsetY() {
		return mapOffsetY;
	}

	public int getMapOffsetX() {
		return mapOffsetX;
	}

	public void setMapOffsetX(int mapOffsetX) {
		this.mapOffsetX = mapOffsetX;
	}
	
	public void setMapOffsetY(int mapOffsetY) {
		this.mapOffsetY = mapOffsetY;
	}

	public int getSliderOffsetX() {
		return sliderOffsetX;
	}
	
	public void updateMapOffsetX(int offsetX){
		this.mapOffsetX+=offsetX;
	}

	public void updateMapOffsetY(int offsetY){
		this.mapOffsetY+=offsetY;
	}
	
	
	
	public void updateZoomLevel(float f,int w, int h,double dragX,double dragY){
		
		Log.d("offset",dragX+" "+dragY);
		
		int x_old = (int)(w*zoomLevel);
		int y_old = (int)(h*zoomLevel);
		
		this.zoomLevel*=f;
		
		int x_new = (int)(w*zoomLevel);
		int y_new = (int)(h*zoomLevel);
		
		int offsetX = -x_old+x_new;
		int offsetY = -y_old+y_new;
		updateMapOffsetX((int)(offsetX*dragX));
		updateMapOffsetY((int)(offsetY*dragY));
	}

	public float getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(float zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public void alignSliderOffsetsToCenter(){
		
		MapLocation activeMapLocation=null;
		if(selection != null && !selection.isEmpty()){
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
		}
		//activeMapLocation.
	}
	
	
	
	public void setSliderOffsetX(int sliderOffsetX) {
		this.sliderOffsetX = sliderOffsetX;
		Tuple<MapLocation,MetaData> nearestToCenter=null;
		
		if(selection != null && !selection.isEmpty()){
			for (Tuple<MapLocation,MetaData> t: selection){
				int locationX = t.a.getSliderPosX() + this.sliderOffsetX;
				if (nearestToCenter == null) nearestToCenter=t;
				
				
				if (Math.abs(locationX) < Math.abs(nearestToCenter.a.getSliderPosX() + this.sliderOffsetX)){
					
					nearestToCenter=t;
				}
				
			}
			this.activeLocationId=nearestToCenter.a.getId();
		}
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
			selectionGestureExecuted();
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

	public void selectionGestureExecuted() {

		Log.d("selection gesture", "estimated selection gesture detected! selection was: "+mapLocs.get(getActiveLocationId()).getName());
		
		Context context = MainActivity.instance;
		CharSequence text = mapLocs.get(getActiveLocationId()).getName()+ " selected!";
		int duration = Toast.LENGTH_SHORT;

		final Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
		Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
		           @Override
		           public void run() {
		               toast.cancel(); 
		           }
		        }, 500);
	}	
	
}
