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
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;


public class MapSurfaceView extends DrawSurfaceView{

	//private MapDataProvider mapProvider;
	private SelectionSurfaceModel m;
	
	public void setSelectionSurfaceModel(SelectionSurfaceModel m){
		this.m = m;
		this.setOnTouchListener(new TouchHandler(m));
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
			int x = (int) (m.getLat()*this.getWidth());
			int y = (int) (m.getLng()*this.getHeight());
			m.setXpos(x);
			m.setYpos(y);
			if (m.getId() == this.m.getActiveLocationId()){
				activeMap=m;
			}
			c.drawCircle(m.getXpos(),m.getYpos(), 10.0f, PAINT_DOT);
		}
		
		if (activeMap!=null){
			c.drawCircle(activeMap.getXpos(),activeMap.getYpos(), 11.0f, PAINT_ACTIVE);
		}
		
		if (m.getSelection()==null)return;
		for (Tuple<MapLocation,MetaData> s: m.getSelection()){
			c.drawCircle(s.a.getXpos(),s.a.getYpos(), 11.0f, PAINT_SELECTION);
			
		}
		
	}
	
	


	

}
