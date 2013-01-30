package de.uulm.mhci.mhci_project.evaluation;

import de.uulm.mhci.mhci_project.classes.entities.MapLocation;

public class SelectionTaskObject {

	private MapLocation mapLoc;
	private long taskStartTime= -1;
	private long taskFinishedTime= -1;
	private long totalTaskTime = -1;
	private boolean taskStarted = false;
	
	public SelectionTaskObject(MapLocation mapLocation) {
		this.mapLoc = mapLocation;
	}
	
	public int getMapLocID(){
		return mapLoc.getId();
	}
	
	public void setStartTime(long timestamp){
		this.taskStartTime = timestamp;
		this.taskStarted = true;
	}
	
	public void setEndTime(long timestamp){
		if(taskStarted){
			this.taskFinishedTime = timestamp;
			calcTaskTime();
		}
	}
	
	private void calcTaskTime(){
		totalTaskTime = taskFinishedTime - taskStartTime;
	}
	
	public long getTotalTaskTime(){
		return totalTaskTime;
	}

}
