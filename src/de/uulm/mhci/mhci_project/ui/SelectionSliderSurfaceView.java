package de.uulm.mhci.mhci_project.ui;

import de.uulm.mhci.mhci_project.classes.controller.SelectionTouchHandler;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;

public class SelectionSliderSurfaceView extends DrawSurfaceView{
	private SelectionSurfaceModel m;
	
	private int drawOffset = 0;
	
	public void setSelectionSurfaceModel(SelectionSurfaceModel m){
		this.m = m;
		setOnTouchListener(new SelectionTouchHandler(m));
	}
	
	
	public SelectionSliderSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private static Paint redFill = new Paint();
	private static Paint yelloFill= new Paint();
	private static Paint blueFill= new Paint();
	
	static{
		redFill.setColor(Color.RED);
		redFill.setStyle(Paint.Style.FILL);

		yelloFill.setColor(Color.YELLOW);
		yelloFill.setStyle(Paint.Style.FILL);
		
		blueFill.setColor(Color.BLUE);
		blueFill.setStyle(Paint.Style.FILL);
	}
	
	@Override
	protected void paint(Canvas c) {
		int middle = this.getWidth()/2;
		int rect_size = this.getHeight()-10;
		if (m.getSelection()==null)return;
		int mlocSize = m.getSelection().size();
		
		int centerItem_left = middle-rect_size/2;
		
		if (mlocSize<2) return;
		int l=0;
		int r=0;
		int i=0;
		for (Tuple<MapLocation,MetaData> t: m.getSelection()){
			
			
			
			if (m.getActiveLocationId() == t.a.getId()){
				//selected
				c.drawRect(centerItem_left, 5, centerItem_left+rect_size, 5+rect_size, blueFill);
			}else{
				i++;
				
				
				int offset=0;
				
				
				if (i<=mlocSize/2){
					l++;
					offset= centerItem_left-( l*(rect_size+5) ) ;
					c.drawRect(offset, 5, offset+rect_size, 5+rect_size, redFill);
					
					
				}else{
					r++;
					offset=( r*(rect_size+5) )+centerItem_left;
					
					c.drawRect(offset, 5, offset+rect_size, 5+rect_size, yelloFill);
					
				}
				
			}
			
			
		}
		
		
	}

}
