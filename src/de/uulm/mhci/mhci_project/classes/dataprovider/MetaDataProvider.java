package de.uulm.mhci.mhci_project.classes.dataprovider;

import de.uulm.mhci.mhci_project.MainActivity;
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
		for(int i = 0; i< MainActivity.AMOUNT_OF_TARGETS; i++){
			switch(i%20){
				case 0:
					metaData.append(i, new MetaData(i,"Food", R.drawable.restaurant)); break;
				case 1:
					metaData.append(i, new MetaData(i,"Food", R.drawable.restaurant)); break;
				case 2:
					metaData.append(i, new MetaData(i,"Food", R.drawable.restaurant)); break;
				case 3: 
					metaData.append(i, new MetaData(i,"Food", R.drawable.restaurant)); break;
				case 4: 
					metaData.append(i, new MetaData(i,"Food", R.drawable.restaurant)); break;
				case 5: 
					metaData.append(i, new MetaData(i,"Lifestyle", R.drawable.lifestyle)); break;
				case 6: 
					metaData.append(i, new MetaData(i,"Lifestyle", R.drawable.lifestyle)); break;
				case 7: 
					metaData.append(i, new MetaData(i,"Lifestyle", R.drawable.lifestyle)); break;
				case 8: 
					metaData.append(i, new MetaData(i,"Lifestyle", R.drawable.lifestyle)); break;
				case 9: 
					metaData.append(i, new MetaData(i,"Lifestyle", R.drawable.lifestyle)); break;
				case 10: 
					metaData.append(i, new MetaData(i,"Bar", R.drawable.bar)); break;
				case 11: 
					metaData.append(i, new MetaData(i,"Bar", R.drawable.bar)); break;
				case 12:
					metaData.append(i, new MetaData(i,"Bar", R.drawable.bar)); break;
				case 13: 
					metaData.append(i, new MetaData(i,"Bar", R.drawable.bar)); break;
				case 14: 
					metaData.append(i, new MetaData(i,"Bar", R.drawable.bar));	 break;
				case 15: 
					metaData.append(i, new MetaData(i,"Shopping", R.drawable.shopping)); break;
				case 16: 
					metaData.append(i, new MetaData(i,"Shopping", R.drawable.shopping)); break;
				case 17: 
					metaData.append(i, new MetaData(i,"Shopping", R.drawable.shopping)); break;
				case 18: 
					metaData.append(i, new MetaData(i,"Shopping", R.drawable.shopping)); break;
				case 19:
					metaData.append(i, new MetaData(i,"Shopping", R.drawable.shopping)); break;
			}
		}
	}
	
	public MetaData getMetaDataFromId(int id) throws NotFoundException{
		MetaData ret = metaData.get(id);
		if (ret == null){
			throw new NotFoundException(String.format("Id %d does not exist",id));
		}
		return ret;
	}
}
