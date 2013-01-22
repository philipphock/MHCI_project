package de.uulm.mhci.mhci_project.ui;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class DrawSurfaceView  extends SurfaceView implements SurfaceHolder.Callback, Runnable{

	private volatile boolean running = true;
	
	private static final Paint PAINT_ERASE = new Paint();
	
	
	static{
		PAINT_ERASE.setStyle(Paint.Style.FILL);
		PAINT_ERASE.setColor(Color.BLACK);
	}
	
	public DrawSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}

	
	
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {

		running=true;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
		running=false;
	}

	@Override
	public void run() {
		while(running){
			if (getHolder().getSurface().isValid()){
				Canvas canvas = getHolder().lockCanvas();
				if (canvas == null){
					running=false;
					break;
				} 
				canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), PAINT_ERASE);
				paint(canvas);
				getHolder().unlockCanvasAndPost(canvas);
			}
		}
		
	}
	
	/**
	 * Use this method to draw on the surface view
	 * @param c
	 */
	protected abstract void paint(Canvas c);

}
