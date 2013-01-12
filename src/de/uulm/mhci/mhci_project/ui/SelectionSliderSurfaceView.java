package de.uulm.mhci.mhci_project.ui;

import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

public class SelectionSliderSurfaceView extends DrawSurfaceView{
	private SelectionSurfaceModel m;
	
	public void setSelectionSurfaceModel(SelectionSurfaceModel m){
		this.m = m;
	}
	
	
	public SelectionSliderSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	
	@Override
	protected void paint(Canvas c) {
		
		
	}

}
