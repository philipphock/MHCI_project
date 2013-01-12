package de.uulm.mhci.mhci_project.classes.controller;


import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SelectionTouchHandler implements OnTouchListener{

	private final SelectionSurfaceModel m;
	public SelectionTouchHandler(SelectionSurfaceModel m) {
		this.m = m;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (m==null) return false;
		switch (event.getAction() & MotionEvent.ACTION_MASK){
		
			case MotionEvent.ACTION_POINTER_DOWN:
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
			
		}
		
		return true;

	}

}
