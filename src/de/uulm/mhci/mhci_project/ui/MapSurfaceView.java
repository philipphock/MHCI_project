package de.uulm.mhci.mhci_project.ui;

import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.controller.TouchHandler;
import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.dataprovider.LocationDataProvider;
import de.uulm.mhci.mhci_project.classes.dataprovider.MapDataProvider;
import de.uulm.mhci.mhci_project.classes.dataprovider.MetaDataProvider;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;


public class MapSurfaceView extends DrawSurfaceView{

	//private MapDataProvider mapProvider;
	private final LocationAreaProcessor lap;
	private Vector<MapLocation> mapLocs;
	
	
	private volatile int touch_x=-1;
	private volatile int touch_y=-1;
	private volatile int activeLocationId=-1;
	
	

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

	private static final Paint PAINT_DOT = new Paint();
	private static final Paint PAINT_TOUCH = new Paint();
	private static final Paint PAINT_ACTIVE = new Paint();
	
	
	static{
		PAINT_DOT.setColor(Color.RED);
		
		PAINT_TOUCH.setColor(Color.GREEN);
		PAINT_TOUCH.setStyle(Paint.Style.FILL);
		PAINT_TOUCH.setAlpha(50);
		
		
		PAINT_ACTIVE.setColor(Color.YELLOW);
		PAINT_ACTIVE.setColor(Color.YELLOW);
		PAINT_ACTIVE.setStyle(Paint.Style.STROKE);
		PAINT_ACTIVE.setStrokeWidth(3);
	}
	
	public MapSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.setOnTouchListener(new TouchHandler(this));
		//this.mapProvider = new MapDataProvider();
		this.lap = new LocationAreaProcessor();
		
		mapLocs=lap.getLocations();
	}

	@Override
	protected void paint(Canvas c) {
		
		if (mapLocs==null) return;
		
		if (!(touch_x==touch_y&&touch_x==-1)){
			c.drawCircle(touch_x, touch_y, LocationAreaProcessor.MAXDIST,PAINT_TOUCH);
		}
		
		MapLocation activeMap=null;
		for (MapLocation m: mapLocs){
			int x = (int) (m.getLat()*this.getWidth());
			int y = (int) (m.getLng()*this.getHeight());
			m.setXpos(x);
			m.setYpos(y);
			if (m.getId()==activeLocationId){
				activeMap=m;
			}
			c.drawCircle(m.getXpos(),m.getYpos(), 10.0f, PAINT_DOT);
		}
		
		if (activeMap!=null){
			c.drawCircle(activeMap.getXpos(),activeMap.getYpos(), 11.0f, PAINT_ACTIVE);
		}
		
		
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
	}

	

}
