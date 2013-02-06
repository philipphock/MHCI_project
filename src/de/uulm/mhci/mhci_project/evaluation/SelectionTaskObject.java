package de.uulm.mhci.mhci_project.evaluation;

import android.util.Log;
import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class SelectionTaskObject {

	private MapLocation mapLoc;
	
	private long taskStartTime= -1;
	private long taskFinishedTime= -1;
	private long totalTaskTime = -1;
	private int nrOfObjectsAroundTask = 0;
	private boolean taskStarted = false;
	
	private boolean normalMethodUsed=false;
	private boolean improvedMethodUsed = false;
	
	private long itaskStartTime= -1;
	private long itaskFinishedTime= -1;
	private long itotalTaskTime = -1;
	private int inrOfObjectsAroundTask = 0;
	private boolean itaskStarted = false;
	

	public SelectionTaskObject(MapLocation mapLocation) {
		this.mapLoc = mapLocation;
	}
	
	public int getMapLocID(){
		return mapLoc.getId();
	}
	
	public synchronized void setStartTime(long timestamp, int objects, boolean improvedMethodUsed){
		if(improvedMethodUsed){
			this.improvedMethodUsed=improvedMethodUsed;
			this.itaskStartTime = timestamp;
			this.inrOfObjectsAroundTask = objects;
			this.itaskStarted = true;
		}else{
			this.normalMethodUsed = true;
			this.taskStartTime = timestamp;
			this.nrOfObjectsAroundTask = objects;
			this.taskStarted = true;
		}
	}
	
	public synchronized void setEndTime(long timestamp, boolean improvedMethodUsed){
		if(improvedMethodUsed){
			if(itaskStarted){
				this.itaskFinishedTime = timestamp;
				calcTaskTime();
			}
			
		}else{			
			if(taskStarted){
				this.taskFinishedTime = timestamp;
				calcTaskTime();
			}
			
		}
		
		Log.d("möp", "Task id: "+getMapLocID()+
				"\nstart (improved): "+taskStartTime+" ("+itaskStartTime+
				")\nend (improved): "+taskFinishedTime+" ("+itaskFinishedTime+
				")\ntotal (improved): "+totalTaskTime+" ("+itotalTaskTime+")");
		
	}
	
	private synchronized void calcTaskTime(){
		if(improvedMethodUsed){
			itotalTaskTime = itaskFinishedTime - itaskStartTime;			
		}else{
			totalTaskTime = taskFinishedTime - taskStartTime;
		}
	}
	
	public long getTotalTaskTime(){
		if(improvedMethodUsed){
			return itotalTaskTime;
		}
		return totalTaskTime;
	}

	public int getObjectsAroundTask() {
		if(improvedMethodUsed){
			return inrOfObjectsAroundTask;
		}
		return nrOfObjectsAroundTask;
	}
	

	public boolean isImprovedMethodUsed() {
		return improvedMethodUsed;
	}

	public void setImprovedMethodUsed(boolean improvedMethodUsed) {
		this.improvedMethodUsed = improvedMethodUsed;
	}



}
