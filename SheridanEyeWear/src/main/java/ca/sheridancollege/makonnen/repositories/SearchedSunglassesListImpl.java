package ca.sheridancollege.makonnen.repositories;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Component;

import ca.sheridancollege.makonnen.beans.Sunglasses;

//allows Spring to detect dependency injection for autowiring
@Component
public class SearchedSunglassesListImpl implements SearchedSunglassesList{
	
	//Attribute that holds a list of 'searched' Sunglasses objects
	private List<Sunglasses> searchedList = new CopyOnWriteArrayList<Sunglasses>();

	//Overridden method to retrieve the searched list of Sunglasses objects
	@Override
	public List<Sunglasses> getSearchedSunglassesList() {
		return searchedList;
	}

	//Overridden method to set the searched list of Sunglasses objects
	@Override
	public void setSearchedSunglassesList(List<Sunglasses> searchedList) {
		this.searchedList = searchedList;		
	}

	//Overridden method to clear the searched list of Sunglasses objects
	@Override
	public void emptySearchedList() {
		searchedList.clear();
	}

}
