package de.uulm.mhci.mhci_project.ui;

import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.controller.ScrollGestureListener;
import de.uulm.mhci.mhci_project.classes.controller.TouchHandler;
import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.dataprovider.LocationDataProvider;
import de.uulm.mhci.mhci_project.classes.dataprovider.MapDataProvider;
import de.uulm.mhci.mhci_project.classes.dataprovider.MetaDataProvider;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;


public class MapSurfaceView extends DrawSurfaceView{

	//private MapDataProvider mapProvider;
	private SelectionSurfaceModel m;
	
	private TouchHandler th;
	private ScrollGestureListener sl;
	
	private ScaleGestureDetector sd;
	private GestureDetector gd;
	public void setSelectionSurfaceModel(SelectionSurfaceModel m){
		this.m = m;
		this.th = new TouchHandler(m);
		this.sl = new ScrollGestureListener(m);
		
		sd = new ScaleGestureDetector(this.getContext(),th);
		gd = new GestureDetector(getContext(),sl);
		//this.setOnTouchListener(th);
		
	}
	
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (this.sd.onTouchEvent(event)){
			//Log.d("scale2","scale");
		}
		
		if (this.th.onTouch(this, event)){
			//Log.d("scale2","select");
		}
		this.gd.onTouchEvent(event);
		//return super.onTouchEvent(event);
		return true;
	}



	private static final Paint PAINT_DOT = new Paint();
	private static final Paint PAINT_TOUCH = new Paint();
	private static final Paint PAINT_ACTIVE = new Paint();
	private static final Paint PAINT_SELECTION = new Paint();
	
	
	static{
		PAINT_DOT.setColor(Color.RED);
		
		PAINT_TOUCH.setColor(Color.GREEN);
		PAINT_TOUCH.setStyle(Paint.Style.FILL);
		PAINT_TOUCH.setAlpha(50);
		
		
		PAINT_ACTIVE.setColor(Color.YELLOW);
		PAINT_ACTIVE.setColor(Color.YELLOW);
		PAINT_ACTIVE.setStyle(Paint.Style.STROKE);
		PAINT_ACTIVE.setStrokeWidth(3);
		
		PAINT_SELECTION.setColor(Color.BLUE);
	}
	
	public MapSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
			
	}

	@Override
	protected void paint(Canvas c) {
		if (this.m == null) return;
		
		Vector<MapLocation> mapLocs = this.m.getMapLocations();
		
		int touch_x = m.getTouch_x();
		int touch_y = m.getTouch_y();
		
		if (mapLocs==null) return;
		
		if (!(touch_x==touch_y&&touch_x==-1)){
			c.drawCircle(touch_x, touch_y, LocationAreaProcessor.MAXDIST,PAINT_TOUCH);
		}
		
		MapLocation activeMap=null;
		for (MapLocation m: mapLocs){
			int x = (int) (m.getLat()*this.getWidth()*this.m.getZoomLevel());
			int y = (int) (m.getLng()*this.getHeight()*this.m.getZoomLevel());
			x-=this.m.getMapOffsetX();
			y-=this.m.getMapOffsetY();
			
			float zoomOffsetX = this.m.getZoomOffsetX()/this.getWidth();
			float zoomOffsetY = this.m.getZoomOffsetY()/this.getHeight();
			
			
					
			m.setXpos(x);
			m.setYpos(y);
			if (m.getId() == this.m.getActiveLocationId()){
				activeMap=m;
			}
			c.drawCircle(m.getXpos(),m.getYpos(), 10.0f*this.m.getZoomLevel(), PAINT_DOT);
		}
		
		if (activeMap!=null){
			c.drawCircle(activeMap.getXpos(),activeMap.getYpos(), 11.0f*this.m.getZoomLevel(), PAINT_ACTIVE);
		}
		
		if (m.getSelection()==null)return;
		for (Tuple<MapLocation,MetaData> s: m.getSelection()){
			c.drawCircle(s.a.getXpos(),s.a.getYpos(), 11.0f*this.m.getZoomLevel(), PAINT_SELECTION);
			
		}
		
	}
	
	


	

}
