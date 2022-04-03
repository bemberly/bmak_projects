package ca.sheridancollege.makonnen.repositories;

import java.util.List;

import ca.sheridancollege.makonnen.beans.Sunglasses;

public interface SearchedSunglassesList {
	
	//abstract method to retrieve the 'searched' list of Sunglasses objects
	public List<Sunglasses> getSearchedSunglassesList();
	
	//abstract method to set a list as the searched list
	public void setSearchedSunglassesList(List<Sunglasses> searchedList);
	
	//abstract method to clear the searched list of Sunglasses objects
	public void emptySearchedList();

}
