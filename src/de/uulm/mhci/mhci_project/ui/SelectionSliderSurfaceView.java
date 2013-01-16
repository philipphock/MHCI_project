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
import android.view.SurfaceHolder;

public class SelectionSliderSurfaceView extends DrawSurfaceView{
	private SelectionSurfaceModel m;
	
	public static volatile int ITEM_SIZE=0;
	public static final int ITEM_SPACE=5;
	public static volatile int MOST_CENTER_POSITION_X=0;
	
	
	public void setSelectionSurfaceModel(SelectionSurfaceModel m){
		this.m = m;
		setOnTouchListener(new SelectionTouchHandler(m));
	}
	
	public int getRectSize(){
		return this.getHeight()-10;
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
		
		int rect_size = ITEM_SIZE;
		if (m.getSelection()==null)return;
		int mlocSize = m.getSelection().size();
		
		
		
		int offsetX = this.m.getSliderOffsetX();
		//int offsetY = this.m.getSliderOffsetY();
		
		if (mlocSize<2) return;
		

		Paint k = new Paint();
		k.setColor(Color.GRAY);
		k.setStyle(Paint.Style.FILL);
		c.drawRect((getWidth()/2)-50,0,(getWidth()/2)+50,getHeight(), k);
		
		
		
		for (Tuple<MapLocation,MetaData> t: m.getSelection()){
			
			int posX=MOST_CENTER_POSITION_X+t.a.getSliderPosX() ;
			int posY=t.a.getSliderPosY();
			Paint p = redFill;
			if (this.m.getActiveLocationId() == t.a.getId()){

				p = blueFill;
			}else{
				
			}
			
			
//			int center_offset = (posX+offsetX)+getWidth()/2;
//			Log.d(VIEW_LOG_TAG, ""+center_offset);
//			if(center_offset < 150 && center_offset > -150){
//				c.drawRect(posX+offsetX,posY, posX+rect_size+offsetX, posY+rect_size, p);
//			}else if(center_offset < 250 && center_offset > -250){
//				c.drawRect(posX+offsetX+5, posY+5, posX+rect_size+offsetX-5, posY+rect_size-5, p);
//			}else if(center_offset < 350 && center_offset > -350){
//				c.drawRect(posX+offsetX+7, posY+7, posX+rect_size+offsetX-7, posY+rect_size-7, p);
//			}else if(center_offset < 450 && center_offset > -450){
//				c.drawRect(posX+offsetX+10, posY+10, posX+rect_size+offsetX-10, posY+rect_size-10, p);
//			}else if(center_offset < 550 && center_offset > -550){
//				c.drawRect(posX+offsetX+12, posY+12, posX+rect_size+offsetX-12, posY+rect_size-12, p);
//			}
			
			int pos = posX+offsetX+ ITEM_SIZE/2-getWidth()/2;
			int scale = pos/10;
			
			if(pos<0){
				p = yelloFill;
				c.drawRect(posX+offsetX-scale,posY-scale, posX+rect_size+offsetX+scale, posY+rect_size+scale, p);
			}else{
				c.drawRect(posX+offsetX+scale,posY+scale, posX+rect_size+offsetX-scale, posY+rect_size-scale, p);
			}
			
		}
		k.setColor(Color.GREEN);
		c.drawCircle(MOST_CENTER_POSITION_X, getHeight()/2, 5.0f, k);
		k.setColor(Color.MAGENTA);
		c.drawCircle(getWidth()/2, getHeight()/2, 5.0f, k);
			
//			if (m.getActiveLocationId() == t.a.getId()){
//				//selected
//				c.drawRect(centerItem_left+offsetX, 5, centerItem_left+rect_size+offsetX, 5+rect_size, blueFill);
//			}else{
//				i++;
//				
//				
//				int offset=0;
//				
//				
//				if (i<=mlocSize/2){
//					l++;
//					offset= centerItem_left-( l*(rect_size+5) ) ;
//					c.drawRect(offset+offsetX, 5, offset+rect_size+offsetX, 5+rect_size, redFill);
//					
//					
//				}else{
//					r++;
//					offset=( r*(rect_size+5) )+centerItem_left;
//					
//					c.drawRect(offset+offsetX, 5, offset+rect_size+offsetX, 5+rect_size, yelloFill);
//					
//				}
//				
//			}
//			
//			
//		}
		
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		ITEM_SIZE=getHeight()-2*ITEM_SPACE;
		MOST_CENTER_POSITION_X = this.getWidth()/2 - ITEM_SIZE/2;
		
		
	}

}
