package de.uulm.mhci.mhci_project.classes.entities;

public class MapLocation {

	private final int id;
	private final String name;
	private final double lat;
	private final double lng;
	
	private int xpos;
	private int ypos;
	
	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	
	public MapLocation(int id, String name, double lat, double lng) {
		this.id=id;
		this.name=name;
		this.lat=lat;
		this.lng=lng;
	}
}
