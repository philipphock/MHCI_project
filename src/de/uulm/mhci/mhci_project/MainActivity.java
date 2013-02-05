package de.uulm.mhci.mhci_project;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.evaluation.Evaluator;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import de.uulm.mhci.mhci_project.ui.SelectionSliderSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Point;

import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity implements Observer{

	public  SelectionSurfaceModel smodel = null;
	private Evaluator e;
	public static MainActivity instance;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		setContentView(R.layout.activity_main);
		
		if (smodel == null){
			MapSurfaceView v = (MapSurfaceView ) findViewById(R.id.mapSurfaceView1);
			SelectionSliderSurfaceView m = (SelectionSliderSurfaceView) findViewById(R.id.selectionSliderSurfaceView1);
			LayoutParams pV = v.getLayoutParams();
			LayoutParams pM = m.getLayoutParams();
			
			Display d = getWindowManager().getDefaultDisplay();
			Point size=new Point();
			d.getSize(size);
			int screenHeight=size.y;
			
			pV.height=screenHeight-230;
			pM.height=120;
			
			v.setLayoutParams(pV);
			m.setLayoutParams(pM);
			
			//smodel = new SelectionSurfaceModel(v,m);
			e = Evaluator.getInstance();
			smodel = new SelectionSurfaceModel(e.getLocationAreaProcessor());
			
			v.setSelectionSurfaceModel(smodel);
			m.setSelectionSurfaceModel(smodel);
			
			
		}
		
		smodel.addObserver(this);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public Vector<MapLocation> getMapLocations() {
		return smodel.getMapLocations();
	}

	public void displayMetaInfo(String s){
		TextView v = (TextView) findViewById(R.id.metaInfoText);
		v.setText(s);
		
	}

	@Override
	protected void onDestroy() {
		if(smodel != null){
			smodel.deleteObserver(this);
		}
		
		super.onDestroy();
	}
	

	
	@Override
	public void update(Observable observable, Object data) {
		if (data.equals(SelectionSurfaceModel.MODEL_CHANGE_EVENT_TYPE.ENABLED)){
			MapSurfaceView v = (MapSurfaceView ) findViewById(R.id.mapSurfaceView1);
			SelectionSliderSurfaceView m = (SelectionSliderSurfaceView) findViewById(R.id.selectionSliderSurfaceView1);
			LayoutParams pV = v.getLayoutParams();
			LayoutParams pM = m.getLayoutParams();
			
			Display d = getWindowManager().getDefaultDisplay();
			Point size=new Point();
			d.getSize(size);
			int screenHeight=size.y;
			
			pV.height=screenHeight-230;
			pM.height=120;
			
			
			//v.setVisibility(View.VISIBLE);
			
			if(smodel.isEnabled()){
				//v.setVisibility(View.VISIBLE);
				pV.height=screenHeight-230;
				pM.height=120;
			}else{
				//v.setVisibility(View.INVISIBLE);
				pV.height=screenHeight-110;
				pM.height=0;
				
			}
			
			v.setLayoutParams(pV);
			m.setLayoutParams(pM);
			m.invalidate();
			v.invalidate();
			
		}
		
	}
}
