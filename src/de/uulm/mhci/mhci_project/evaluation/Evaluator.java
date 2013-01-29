package de.uulm.mhci.mhci_project.evaluation;

public class Evaluator {
	
	private long firstTapTime;
	private long selectionTapTime;
	
	public static Evaluator instance;
	
	
	
	public static Evaluator init(){
		if(instance==null){
			instance = new Evaluator();
		}
		return instance;
	}
	
	public Evaluator(){
		
	}
	
	public void setFirstTapTime(long timestamp){
		firstTapTime = timestamp;
	}
	
	public void setSelectionTapTime(long timestamp){
		selectionTapTime = timestamp;
	}
	
	public long getSelectionTime(){
		return selectionTapTime-firstTapTime;
	}
	
	

}
