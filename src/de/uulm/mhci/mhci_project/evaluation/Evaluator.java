package de.uulm.mhci.mhci_project.evaluation;

import java.util.Random;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import de.uulm.mhci.mhci_project.MainActivity;
import de.uulm.mhci.mhci_project.classes.dataprocessor.LocationAreaProcessor;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;
import de.uulm.mhci.mhci_project.model.SelectionSurfaceModel;

public class Evaluator {
	
	private static final int SELECTION_COUNT = 5;
	private boolean improvedSelectionModeActivated = true;
	

	private int currentSelectionTask = 0;	
	
	private static Evaluator instance = null;
	
	private Vector<SelectionTaskObject> selectionTask;
	
	private LocationAreaProcessor lap;

	private SelectionSurfaceModel smodel;
	
	private boolean allTasksNormalDone = false;
	private boolean allTasksImprovedDone = false;
	
	
	private AlertDialog.Builder builder;
	
	
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
		
		builder = new AlertDialog.Builder(MainActivity.instance);

	}
	
	public void startNextTask(int nrOfObjectsInSelection){

		
		if(currentSelectionTask < 0) return;
//		if(currentSelectionTask >= selectionTask.size()){
//			Log.d("evaluation", "bitch me pls!");
//			if(allTasksNormalDone && allTasksImprovedDone){
//				currentSelectionTask = -1;
//				return;
//			}else{
//				return;
//			}
////			Log.d("evaluation", "All tasks completed!");
////			currentSelectionTask = -1;
////			return;
//		}
		selectionTask.get(currentSelectionTask).setStartTime(System.currentTimeMillis(), nrOfObjectsInSelection, improvedSelectionModeActivated);
	}
	
	public void endCurrentTask(){

		
		selectionTask.get(currentSelectionTask).setEndTime(System.currentTimeMillis(), improvedSelectionModeActivated);
		Log.d("evaluation", "Task nr. "+currentSelectionTask+" completed. Improved method used: "+selectionTask.get(currentSelectionTask).isImprovedMethodUsed()+" Time: "+selectionTask.get(currentSelectionTask).getTotalTaskTime()+ " Locations around Target: "+ selectionTask.get(currentSelectionTask).getObjectsAroundTask());
		if(currentSelectionTask<0)return;
		currentSelectionTask++;	
		
		Log.d("mimimi", "improved: "+allTasksImprovedDone+", normal: "+allTasksNormalDone);
		
		if (currentSelectionTask >= selectionTask.size()){
			
			if(improvedSelectionModeActivated){
				allTasksImprovedDone = true;
				String txt = "Congratulations you've selected all targets with our improved mode :)";
				if(!allTasksNormalDone){
					txt = txt + ("\nLets go on with default selection!");
				}
				builder.setMessage(txt);
				currentSelectionTask = 0;
			}else{
				allTasksNormalDone = true;
				String txt = "Congrats you've selected all targets with default selection mode.";
				if(!allTasksImprovedDone){
					txt = txt + ("\nLets go on with the improved selection!");
				}
				builder.setMessage(txt);
				currentSelectionTask = 0;
			}
				
			builder.setPositiveButton("OK", new OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {}});

				
			if(allTasksImprovedDone && allTasksNormalDone){
				Log.d("MUHAHAHAHAHHA", "it works! motherfuckaaaa");
				builder.setMessage("All targets selected! Task complete.\n Thanks for your participation!\n Our improved method will be activated for playing now ;)");
				if(!improvedSelectionModeActivated){
					toggleSelectionMode();
				}
				currentSelectionTask = -1;
			}else{
				toggleSelectionMode();	
			}
			AlertDialog ad = builder.create();
			ad.show();

		}
	}
	
	public int getCurrentTaskID(){
		if(currentSelectionTask<selectionTask.size() && currentSelectionTask >= 0){
			return selectionTask.get(currentSelectionTask).getMapLocID();
		}else{
			return -1; //currentSelectionTask;
		}
	}
	
	
	public LocationAreaProcessor getLocationAreaProcessor(){
		return lap;
	}
	
	public void toggleSelectionMode(){
		if(smodel!=null){
			smodel.setEnabled(!smodel.isEnabled());
			this.improvedSelectionModeActivated = !improvedSelectionModeActivated;
			smodel.setActiveLocationId(null);
		}
	}

	public void setSelectionSurfaceModel(SelectionSurfaceModel smodel) {
		this.smodel = smodel;
//		this.smodel.setEnabled(improvedSelectionModeActivated);		
	}

	public boolean isImprovedSelectionModeActivated() {
		return improvedSelectionModeActivated;
	}
	

}
