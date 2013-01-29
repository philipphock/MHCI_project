package de.uulm.mhci.mhci_project.classes.controller;


import de.uulm.mhci.mhci_project.R;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SelectionTouchHandler implements OnTouchListener{

	private final SelectionSurfaceModel m;
	private boolean fingerDown=false;
	private int posX=-1;
	private int posY=-1;
	
	public SelectionTouchHandler(SelectionSurfaceModel m) {
		this.m = m;
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (m==null) return false;
		switch (event.getAction() & MotionEvent.ACTION_MASK){
		
			case MotionEvent.ACTION_POINTER_DOWN:
			case MotionEvent.ACTION_DOWN:
				fingerDown=true;
				posX = (int) event.getX();
				posY = (int) event.getY();
				break;
				
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_POINTER_UP:
				fingerDown=false;
				this.m.alignSliderOffsetsToCenter();
				this.m.setSliderOffsetY(0);
				break;
				
			case MotionEvent.ACTION_MOVE:
				if (fingerDown){
					int c_x = (int) event.getX();
					int c_y = (int) event.getY();
					
					int offsetX = c_x-posX; 
					int offsetY = c_y-posY;
					
					this.m.setSliderOffsetX(offsetX);
					if(offsetY<0 && offsetY>-150 && (v.getWidth()/2)-50 < posX && (v.getWidth()/2)+50 > posX){
						this.m.setSliderOffsetY(offsetY);
						if(offsetY <= -1*v.getHeight()){
							m.selectionGestureExecuted();
						}
					}else{
						this.m.setSliderOffsetY(0);
					}
					
				}
				break;
			
		}
		
		return true;

	}

}
