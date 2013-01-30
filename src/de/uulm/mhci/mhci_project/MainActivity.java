package de.uulm.mhci.mhci_project;

import java.util.Vector;

import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.evaluation.Evaluator;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;
import de.uulm.mhci.mhci_project.ui.MapSurfaceView;
import de.uulm.mhci.mhci_project.ui.SelectionSliderSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private SelectionSurfaceModel smodel = null;
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
			
			//smodel = new SelectionSurfaceModel(v,m);
			e = Evaluator.getInstance();
			smodel = new SelectionSurfaceModel(e.getLocationAreaProcessor());
			
			v.setSelectionSurfaceModel(smodel);
			m.setSelectionSurfaceModel(smodel);
			
		}
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

}
