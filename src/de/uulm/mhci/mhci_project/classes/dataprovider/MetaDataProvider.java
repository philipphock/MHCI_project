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
		metaData.append(1, new MetaData(1,"food"));
		metaData.append(2, new MetaData(2,"food"));
		metaData.append(3, new MetaData(3,"food"));
		metaData.append(4, new MetaData(4,"food"));
		
		metaData.append(5, new MetaData(5,"Lifestyle"));
		metaData.append(6, new MetaData(5,"Bar"));
		
	}
	
	public MetaData getMetaDataFromId(int id) throws NotFoundException{
		MetaData ret = metaData.get(id);
		if (ret == null){
			throw new NotFoundException(String.format("Id %d does not exist",id));
		}
		return ret;
	}
}
