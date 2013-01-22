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
		this.th = new TouchHandler(m,this);
		this.sl = new ScrollGestureListener(m,this);
		
		sd = new ScaleGestureDetector(this.getContext(),th);
		gd = new GestureDetector(getContext(),sl);
		//this.setOnTouchListener(th);
		
	}
	
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//boolean ret=false;
		if (this.sd.onTouchEvent(event)){
			//Log.d("scale2","scale");
			//ret = true;
		}
		
		
		if (this.gd.onTouchEvent(event)){
			//ret = true;
			//Log.d("scale2","scroll");
		}
		//return super.onTouchEvent(event);
		//if (ret) return true;
		
		if (this.th.onTouch(this, event)){
			//Log.d("scale2","select");
		}
		
		return true;
	}


	

	private static final Paint PAINT_DOT = new Paint();
	private static final Paint PAINT_TOUCH = new Paint();
	private static final Paint PAINT_ACTIVE = new Paint();
	private static final Paint PAINT_SELECTION = new Paint();
	
	public float getZoomCalcWidth() {
		return zoomCalcWidth;
	}




	public void setZoomCalcWidth(float zoomCalcWidth) {
		this.zoomCalcWidth = zoomCalcWidth;
	}




	public float getZoomCalcHeight() {
		return zoomCalcHeight;
	}




	public void setZoomCalcHeight(float zoomCalcHeight) {
		this.zoomCalcHeight = zoomCalcHeight;
	}



	private float zoomCalcWidth=0;
	private float zoomCalcHeight=0;
	
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
		
		float zoom = this.m.getZoomLevel();
		
		Vector<MapLocation> mapLocs = this.m.getMapLocations();
		
		int touch_x = m.getTouch_x();
		int touch_y = m.getTouch_y();
		
		if (mapLocs==null) return;
		
		if (!(touch_x==touch_y&&touch_x==-1)){
			c.drawCircle(touch_x, touch_y, LocationAreaProcessor.MAXDIST,PAINT_TOUCH);
		}
		zoomCalcWidth = this.getWidth()*zoom;
		zoomCalcHeight = this.getHeight()*zoom;
		MapLocation activeMap=null;
		for (MapLocation m: mapLocs){

			/*
			int x = (int) (m.getLat()*zoomCalcWidth);
			int y = (int) (m.getLng()*zoomCalcHeight);
			
			
			
			x-=this.m.getMapOffsetX();
			y-=this.m.getMapOffsetY();
			*/
			
			int x = getMapPosX(m.getLat());
			int y = getMapPosY(m.getLng());
			
					
			m.setXpos(x);
			m.setYpos(y);
			if (m.getId() == this.m.getActiveLocationId()){
				activeMap=m;
			}
			c.drawCircle(m.getXpos(),m.getYpos(), 10.0f*zoom, PAINT_DOT);
		}
		
		if (activeMap!=null){
			c.drawCircle(activeMap.getXpos(),activeMap.getYpos(), 11.0f*zoom, PAINT_ACTIVE);
		}
		
		if (m.getSelection()==null)return;
		for (Tuple<MapLocation,MetaData> s: m.getSelection()){
			c.drawCircle(s.a.getXpos(),s.a.getYpos(), 11.0f*zoom, PAINT_SELECTION);
			
		}
		
		//c.drawRect(this.m.getZoomOffsetX(), this.m.getZoomOffsetY(), this.m.getZoomOffsetX()+10, this.m.getZoomOffsetY()+10, PAINT_ACTIVE);
		c.drawRect(getMapPosX(0.5), getMapPosY(0.5),getMapPosX(0.5)+5,getMapPosY(0.5)+5, PAINT_ACTIVE);
		
	}
	
	/**
	 * converts a value 0..1 to MapCoordinate, including scoll and zoom
	 * @param f
	 * @return
	 */
	public int getMapPosX(double f){
		return (int) ( (this.getWidth()*this.m.getZoomLevel()*f)-this.m.getMapOffsetX() );
	}
	
	/**
	 * converts a value 0..1 to MapCoordinate, including scoll and zoom
	 * @param f
	 * @return
	 */
	public int getMapPosY(double f){
		return (int) ( (this.getHeight()*this.m.getZoomLevel()*f)-this.m.getMapOffsetY() );
	}

	/**
	 * converts a value on screen (px) to a relative mapcoord (0..1)
	 * respecting scroll and zoom
	 * @param p
	 * @return
	 */
	public double toAbsoluteX(int p){
		return (((p+this.m.getMapOffsetX())/(float)getWidth())/this.m.getZoomLevel()) ;
		
	}
	public double toAbsoluteY(int p){
		return (((p+this.m.getMapOffsetY())/(float)getHeight())/this.m.getZoomLevel()) ;
	}
	
	
	

}
