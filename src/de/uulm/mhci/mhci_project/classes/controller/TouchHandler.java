package de.uulm.mhci.mhci_project.classes.controller;


import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import android.gesture.GestureOverlayView.OnGestureListener;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchHandler extends ScaleGestureDetector.SimpleOnScaleGestureListener implements OnTouchListener{

	private final SelectionSurfaceModel m;
	private final MapSurfaceView v;
	public TouchHandler(SelectionSurfaceModel m,MapSurfaceView v) {
		this.m = m;
		this.v = v;
		
		
		
	}
	
	@Override
	public boolean onScaleBegin(ScaleGestureDetector detector) {
		return true;
	}
	
	
	
	@Override
	public boolean onScale(ScaleGestureDetector detector) {
		float scaleFactor = detector.getScaleFactor();

		
		
		this.m.setZoomOffsetX((int)detector.getFocusX());
		this.m.setZoomOffsetY((int)detector.getFocusY());
		
		
	
		
        this.m.updateZoomLevel(scaleFactor,v.getWidth(),v.getHeight());
        
    
        
		//return super.onScale(detector);
        return true;
	}
	
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (m==null) return false;
		switch (event.getAction() & MotionEvent.ACTION_MASK){
		
			case MotionEvent.ACTION_POINTER_DOWN:
				
				
			case MotionEvent.ACTION_DOWN:
			break;
			
			case MotionEvent.ACTION_UP:
				this.m.setTouch_x((int)event.getX());
				this.m.setTouch_y((int)event.getY());
				
				
				
				double xrel=event.getX()/v.getWidth();
				double yrel=event.getY()/v.getHeight();
				
				
				this.m.click((int)event.getX(),(int)event.getY(),xrel,yrel);
				break;
			case MotionEvent.ACTION_POINTER_UP:
				
			//LocationAreaProcessor	
		}
		
		return true;

	}

	
}
