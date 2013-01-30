package de.uulm.mhci.mhci_project.evaluation;

import java.util.Random;
import java.util.Vector;

import android.util.Log;

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
			
			if(!selectionTask.contains(mapLocs.get(k))){
				selectionTask.add(new SelectionTaskObject(mapLocs.get(k)));
				Log.d("Task added", "Tast #"+i+" , id select: "+selectionTask.get(i).getMapLocID()+" , id loc: "+mapLocs.get(k).getId()+" named "+ mapLocs.get(k).getName());
			}else{
				i = i-1;
			}		
		}
		
	}
	
	public void startNextTask(){
		if(currentSelectionTask >= selectionTask.size()){
			Log.d("evaluation", "All tasks completed!");
			currentSelectionTask = -1;
			return;
		}
		selectionTask.get(currentSelectionTask).setStartTime(System.currentTimeMillis());
	}
	
	public void endCurrentTask(){
		selectionTask.get(currentSelectionTask).setEndTime(System.currentTimeMillis());
		Log.d("evaluation", "Task nr. "+currentSelectionTask+" completed. Time to complete selection: "+selectionTask.get(currentSelectionTask).getTotalTaskTime());
		currentSelectionTask++;		
		if (currentSelectionTask >= selectionTask.size()){
			currentSelectionTask = -1;
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
