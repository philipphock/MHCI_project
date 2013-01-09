package de.uulm.mhci.mhci_project.classes.dataprovider;

import de.uulm.mhci.mhci_project.classes.entities.MetaData;
import android.content.res.Resources.NotFoundException;
import android.util.SparseArray;

public class MetaDataProvider {

	private final SparseArray<MetaData> metaData;
	
	public MetaDataProvider() {
		metaData = new SparseArray<MetaData>();
		createNewValues();
	} 
	
	private void createNewValues(){
		metaData.append(0, new MetaData(0,"food"));
		metaData.append(0, new MetaData(1,"food"));
		metaData.append(0, new MetaData(2,"food"));
		metaData.append(0, new MetaData(3,"food"));
		metaData.append(0, new MetaData(4,"food"));
		
	}
	
	public MetaData getMetaDataFromId(int id) throws NotFoundException{
		MetaData ret = metaData.get(id);
		if (ret == null){
			throw new NotFoundException(String.format("Id %i does not exist",id));
		}
		return ret;
	}
}
