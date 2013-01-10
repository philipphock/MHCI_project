package de.uulm.mhci.mhci_project.classes.entities;



public class MetaData {
	public String getCategory() {
		return category;
	}

	private final int id;
	private final String category;
	
	public MetaData(int id,String category) {
		this.id=id;
		this.category=category;
	}
}

