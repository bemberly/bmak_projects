package ca.sheridancollege.makonnen.repositories;

import java.util.List;

import ca.sheridancollege.makonnen.beans.Sunglasses;

public interface SunglassesList {
	
	
	//abstract method to retrieve the list of Sunglasses objects
	public List<Sunglasses> getSunglassesList();
	
	//abstract method to set a list as the sunglasses list
	public void setSunglassesList(List<Sunglasses> sunglassesList);
	
	//abstract method to clear the list of Sunglasses objects
	public void emptyList();

}
