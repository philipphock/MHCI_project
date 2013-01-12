package de.uulm.mhci.mhci_project.classes.controller;


import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TouchHandler implements OnTouchListener{

	private final SelectionSurfaceModel m;
	public TouchHandler(SelectionSurfaceModel m) {
		this.m = m;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (m==null) return false;
		switch (event.getAction() & MotionEvent.ACTION_MASK){
		
			case MotionEvent.ACTION_POINTER_DOWN:
			case MotionEvent.ACTION_DOWN:
				this.m.setTouch_x((int)event.getX());
				this.m.setTouch_y((int)event.getY());
				double xrel=event.getX()/v.getWidth();
				double yrel=event.getY()/v.getHeight();
				
				this.m.click((int)event.getX(),(int)event.getY(),xrel,yrel);
			break;
			
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				
			//LocationAreaProcessor	
		}
		
		return true;

	}

}
