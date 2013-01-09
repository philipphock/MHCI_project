package de.uulm.mhci.mhci_project.ui;

import de.uulm.mhci.mhci_project.classes.controller.TouchHandler;
import de.uulm.mhci.mhci_project.classes.dataprovider.LocationDataProvider;
import de.uulm.mhci.mhci_project.classes.dataprovider.MapDataProvider;
import de.uulm.mhci.mhci_project.classes.dataprovider.MetaDataProvider;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;


public class MapSurfaceView extends DrawSurfaceView{

	private MapDataProvider mapProvider;
	
	public MapSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnTouchListener(new TouchHandler());
		this.mapProvider = new MapDataProvider();
	}

	@Override
	protected void paint(Canvas c) {
		
		
	}

	

}
