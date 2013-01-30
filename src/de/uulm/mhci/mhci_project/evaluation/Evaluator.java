package de.uulm.mhci.mhci_project.evaluation;

import java.util.Random;
import java.util.Vector;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import de.uulm.mhci.mhci_project.MainActivity;
import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class Evaluator {
	
	private static final int SELECTION_COUNT = 5;
	
	private int currentSelectionTask = 0;
	
	
	private static Evaluator instance = null;
	
	private Vector<SelectionTaskObject> selectionTask;
	
	private LocationAreaProcessor lap;
	
	
	public static Evaluator getInstance(){
		if(instance==null){
			instance = new Evaluator();
		}
		return instance;
	}
	
	private Evaluator(){
		lap = new LocationAreaProcessor();
		Vector<MapLocation> mapLocs = lap.getLocations();
		Random r = new Random();
		selectionTask = new Vector<SelectionTaskObject>();
		
		for(int i = 0; i <SELECTION_COUNT; i++){
			
			int k = r.nextInt(mapLocs.size());
			
			SelectionTaskObject tmp = new SelectionTaskObject(mapLocs.get(k));
			if(!selectionTask.contains(tmp)){
				selectionTask.add(tmp);
				Log.d("evaluation", "Task #"+i+" , id select: "+selectionTask.get(i).getMapLocID()+" named "+ mapLocs.get(k).getName());
			}else{
				i = i-1;
			}		
		}
		
	}
	
	public void startNextTask(int nrOfObjectsInSelection){
		if(currentSelectionTask >= selectionTask.size()){
			Log.d("evaluation", "All tasks completed!");
			currentSelectionTask = -1;
			return;
		}
		selectionTask.get(currentSelectionTask).setStartTime(System.currentTimeMillis(), nrOfObjectsInSelection);
	}
	
	public void endCurrentTask(){
		selectionTask.get(currentSelectionTask).setEndTime(System.currentTimeMillis());
		Log.d("evaluation", "Task nr. "+currentSelectionTask+" completed. Time to complete selection: "+selectionTask.get(currentSelectionTask).getTotalTaskTime()+ " Locations around Target: "+ selectionTask.get(currentSelectionTask).getObjectsAroundTask());
		currentSelectionTask++;		
		if (currentSelectionTask >= selectionTask.size()){
			currentSelectionTask = -1;
			Context context = MainActivity.instance;
			CharSequence text = "All targets selected! Task complete.\n Thanks for your participation!";
			int duration = Toast.LENGTH_LONG;
			Log.d("evaluation","All targets selected");

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
		}
	}
	
	public int getCurrentTaskID(){
		if(currentSelectionTask<selectionTask.size() && currentSelectionTask > 0){
			return selectionTask.get(currentSelectionTask).getMapLocID();
		}else{
			return currentSelectionTask;
		}
	}
	
	
	public LocationAreaProcessor getLocationAreaProcessor(){
		return lap;
	}
	

}
