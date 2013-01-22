package de.uulm.mhci.mhci_project.classes.dataprocessor;

import android.content.res.Resources.NotFoundException;
import de.uulm.mhci.mhci_project.classes.dataprovider.MetaDataProvider;
import de.uulm.mhci.mhci_project.classes.entities.MetaData;

public class MetaDataProcessor {
	private MetaDataProvider metaProvider;
	
	public MetaDataProcessor() {
		metaProvider = new MetaDataProvider();
	}
	
	public MetaData getMetaDataFromId(int id) throws NotFoundException{
		return metaProvider.getMetaDataFromId(id);
	
	}
}
