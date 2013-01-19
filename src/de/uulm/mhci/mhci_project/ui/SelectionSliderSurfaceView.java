package de.uulm.mhci.mhci_project.ui;

import de.uulm.mhci.mhci_project.R;
import de.uulm.mhci.mhci_project.classes.controller.SelectionTouchHandler;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import de.uulm.mhci.mhci_project.classes.entities.Tuple;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import android.R.color;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;

public class SelectionSliderSurfaceView extends DrawSurfaceView{
	private SelectionSurfaceModel m;
	
	public static volatile int ITEM_SIZE=0;
	public static final int ITEM_SPACE=5;
	public static volatile int MOST_CENTER_POSITION_X=0;

	public static Resources RESOURCE;
	
	public void setSelectionSurfaceModel(SelectionSurfaceModel m){
		this.m = m;
		setOnTouchListener(new SelectionTouchHandler(m));
	}
	
	public int getRectSize(){
		return this.getHeight()-10;
	}
	
	public SelectionSliderSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		RESOURCE = getResources();
//		bm = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
	}

	private static Paint redFill = new Paint();
	private static Paint yelloFill= new Paint();
	private static Paint blueFill= new Paint();
	
	static{
		redFill.setColor(Color.RED);
		redFill.setStyle(Paint.Style.FILL);
		redFill.setStrokeWidth(5);

		yelloFill.setColor(Color.YELLOW);
		yelloFill.setStyle(Paint.Style.FILL);
		yelloFill.setStrokeWidth(5);
		
		blueFill.setColor(Color.BLUE);
		blueFill.setStyle(Paint.Style.FILL);
		blueFill.setStrokeWidth(5);
	}
	
	@Override
	protected void paint(Canvas c) {
		Paint k = new Paint();
		k.setColor(Color.DKGRAY);
		c.drawRect(0, 0, getWidth(), getHeight(), k);
		
		int rect_size = ITEM_SIZE;
		if (m.getSelection()==null)return;
		int mlocSize = m.getSelection().size();
		
		
		
		int offsetX = this.m.getSliderOffsetX();
		//int offsetY = this.m.getSliderOffsetY();
		
		if (mlocSize<2) return;
		

		
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
					
			int pos = posX+offsetX+ ITEM_SIZE/2-getWidth()/2;
			int scale = pos/30;
			RectF r = new RectF();
			if(pos<0){
				p = yelloFill;
				r.set(posX+offsetX-scale,posY-scale, posX+rect_size+offsetX+scale, posY+rect_size+scale);
//				c.drawRoundRect(r, 0.3f, 0.3f, p);
			}else{
//				c.drawRect(posX+offsetX+scale,posY+scale, posX+rect_size+offsetX-scale, posY+rect_size-scale, p);
				
				r.set(posX+offsetX+scale,posY+scale, posX+rect_size+offsetX-scale, posY+rect_size-scale);
			}
			c.drawRoundRect(r, 3f, 3f, p);
			Bitmap bm = t.b.getBitmapResource();
			if(bm!=null) c.drawBitmap(bm, null, r, p);
			
		}
		/**
		 * Draw center and "most-center" circles (just for orientation
		 
		k.setColor(Color.GREEN);
		c.drawCircle(MOST_CENTER_POSITION_X, getHeight()/2, 5.0f, k);
		k.setColor(Color.MAGENTA);
		c.drawCircle(getWidth()/2, getHeight()/2, 5.0f, k);
		*/	
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
