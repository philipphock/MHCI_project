package de.uulm.mhci.mhci_project.classes.controller;

import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;

import android.view.MotionEvent;
import android.view.GestureDetector.SimpleOnGestureListener;

public class ScrollGestureListener extends SimpleOnGestureListener{
	
	private final SelectionSurfaceModel m;
	private final MapSurfaceView v;
	
	public ScrollGestureListener(SelectionSurfaceModel m, MapSurfaceView v){
		this.m=m;
		this.v=v;
	}
	
	
	 @Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		
		 
		m.updateMapOffsetX((int)distanceX);
		m.updateMapOffsetY((int)distanceY);
		
		return super.onScroll(e1, e2, distanceX, distanceY);
	}
}
