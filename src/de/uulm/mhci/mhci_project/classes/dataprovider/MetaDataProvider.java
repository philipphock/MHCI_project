package de.uulm.mhci.mhci_project.classes.dataprovider;

import de.uulm.mhci.mhci_project.R;
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
		metaData.append(0, new MetaData(0,"Food", R.drawable.restaurant));
		metaData.append(1, new MetaData(1,"Food", R.drawable.restaurant));
		metaData.append(2, new MetaData(2,"Food", R.drawable.restaurant));
		metaData.append(3, new MetaData(3,"Food", R.drawable.restaurant));
		metaData.append(4, new MetaData(4,"Food", R.drawable.restaurant));
		
		metaData.append(5, new MetaData(5,"Lifestyle", R.drawable.lifestyle));
		metaData.append(6, new MetaData(6,"Lifestyle", R.drawable.lifestyle));
		metaData.append(7, new MetaData(7,"Lifestyle", R.drawable.lifestyle));
		metaData.append(8, new MetaData(8,"Lifestyle", R.drawable.lifestyle));
		metaData.append(9, new MetaData(9,"Lifestyle", R.drawable.lifestyle));
		
		metaData.append(10, new MetaData(10,"Bar", R.drawable.bar));
		metaData.append(11, new MetaData(11,"Bar", R.drawable.bar));
		metaData.append(12, new MetaData(12,"Bar", R.drawable.bar));
		metaData.append(13, new MetaData(13,"Bar", R.drawable.bar));
		metaData.append(14, new MetaData(14,"Bar", R.drawable.bar));		
		
		metaData.append(15, new MetaData(15,"Shopping", R.drawable.shopping));
		metaData.append(16, new MetaData(16,"Shopping", R.drawable.shopping));
		metaData.append(17, new MetaData(17,"Shopping", R.drawable.shopping));
		metaData.append(18, new MetaData(18,"Shopping", R.drawable.shopping));
		metaData.append(19, new MetaData(19,"Shopping", R.drawable.shopping));
		
		
	}
	
	public MetaData getMetaDataFromId(int id) throws NotFoundException{
		MetaData ret = metaData.get(id);
		if (ret == null){
			throw new NotFoundException(String.format("Id %d does not exist",id));
		}
		return ret;
	}
}
