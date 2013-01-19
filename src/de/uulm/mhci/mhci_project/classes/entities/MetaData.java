package de.uulm.mhci.mhci_project.classes.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import de.uulm.mhci.mhci_project.R;
import de.uulm.mhci.mhci_project.ui.SelectionSliderSurfaceView;



public class MetaData {
	public String getCategory() {
		return category;
	}

	private final int id;
	private final String category;
	private final Bitmap bmp;
	
	public MetaData(int id,String category) {
		this.id=id;
		this.category=category;
		this.bmp = BitmapFactory.decodeResource(SelectionSliderSurfaceView.RESOURCE, R.drawable.icon3);
		
	}
	
	public MetaData(int id,String category, int bitmapID) {
		this.id=id;
		this.category=category;
		this.bmp = BitmapFactory.decodeResource(SelectionSliderSurfaceView.RESOURCE, bitmapID);
	}

	public Bitmap getBitmapResource() {
		// TODO Return the correct icon id! atm default value (reference to R.drawable.<img>)
		return bmp;
	}
}

