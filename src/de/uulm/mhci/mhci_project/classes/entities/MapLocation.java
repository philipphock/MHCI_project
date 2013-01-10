package de.uulm.mhci.mhci_project.classes.entities;

public class MapLocation {

	public String getName() {
		return name;
	}
	public double getLat() {
		return lat;
	}
	public double getLng() {
		return lng;
	}

	private final int id;
	private final String name;
	private double lat;
	
	public void setLat(double lat) {
		this.lat = lat;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

	private double lng;
	
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
	
	public int getId(){
		return id;
	}
	
	public MapLocation(int id, String name, double lat, double lng) {
		this.id=id;
		this.name=name;
		this.lat=lat;
		this.lng=lng;
	}
}
