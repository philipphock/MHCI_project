package de.uulm.mhci.mhci_project.classes.controller;


import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchHandler implements OnTouchListener{

	private final MapSurfaceView v;
	public TouchHandler(MapSurfaceView v) {
		this.v = v;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
			
		switch (event.getAction() & MotionEvent.ACTION_MASK){
		
			case MotionEvent.ACTION_POINTER_DOWN:
			case MotionEvent.ACTION_DOWN:
				this.v.setTouch_x((int)event.getX());
				this.v.setTouch_y((int)event.getY());
				double xrel=event.getX()/v.getWidth();
				double yrel=event.getY()/v.getHeight();
				
				this.v.click((int)event.getX(),(int)event.getY(),xrel,yrel);
			break;
			
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				
			//LocationAreaProcessor	
		}
		
		return true;

	}

}
