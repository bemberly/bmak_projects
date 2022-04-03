package ca.sheridancollege.makonnen.repositories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import ca.sheridancollege.makonnen.beans.Sunglasses;

//allows Spring to detect dependency injection autowiring
@Component
public class SunglassesListImpl implements SunglassesList {
	
	//Attribute that holds a list of Sunglasses objects
	private List<Sunglasses> sunglassesList = new CopyOnWriteArrayList<Sunglasses>();

	//Overridden method to retrieve the list of Sunglasses objects
	@Override
	public List<Sunglasses> getSunglassesList() {
		return sunglassesList;
	}

	//Overridden method to set the list of Sunglasses objects
	@Override
	public void setSunglassesList(List<Sunglasses> sunglassesList) {
		this.sunglassesList = sunglassesList;		
	}

	//Overridden method to clear the list of Sunglasses objects
	@Override
	public void emptyList() {
		sunglassesList.clear();
	}

}
