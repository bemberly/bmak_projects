package ca.sheridancollege.makonnen.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ca.sheridancollege.makonnen.beans.BrandSearch;
import ca.sheridancollege.makonnen.beans.Sunglasses;
import ca.sheridancollege.makonnen.repositories.SearchedSunglassesList;
import ca.sheridancollege.makonnen.repositories.SunglassesList;

@Controller
public class EyeWearController {
	
	//Declares list that holds a list of sunglass objects
	private SunglassesList sunglassesList;
	
	//Declares list that holds a list of sunglasses matching brand search
	private SearchedSunglassesList searchedList;
	
	//Dependency injection of two component classes upon controller creation
	@Autowired
	public EyeWearController(SunglassesList sunglassesList, 
			SearchedSunglassesList searchedList) {
		this.sunglassesList = sunglassesList;
		this.searchedList = searchedList;
	}
	
	//Handles the HTTP GET request matched with the url for the home page
	@GetMapping("/")
	public String goHome() {
		
		return "index";
	}
	
	//Handles the HTTP GET request matched with the url for the adding page
	@GetMapping("/add")
	public String addSunglasses(Model model) {
		
		//adds a new Sunglasses object to the model
		model.addAttribute("sunglasses", new Sunglasses());
		
		//adds the sunglasses list object to the model
		model.addAttribute("sunglassesList", 
				sunglassesList.getSunglassesList());
		
		return "add";
	}
	
	//Handles the HTTP GET request matched with the url for the search page	
	@GetMapping("/search")
	public String searchSunglass(Model model) {
		
		//adds a new BrandSearch object to the model
		model.addAttribute("search", new BrandSearch());

		//adds the searched sunglasses list object to the model
		model.addAttribute("searchedList", searchedList.getSearchedSunglassesList());
		
		//adds the sunglasses list object to the model
		model.addAttribute("sunglassesList", sunglassesList.getSunglassesList());
				
		return "search";
	}
	
	//Handles the HTTP GET request matched with the url for the display page	
	@GetMapping("/display")
	public String displaySunglass(Model model) {
		
		//adds the sunglasses list object to the model
		model.addAttribute("sunglassesList", sunglassesList.getSunglassesList());
		
		return "display";
	}
	
	
	//Handles the HTTP POST request matched with 'processSearch' url expression
	@PostMapping("/processSearch")
	public String processSearch(Model model, @ModelAttribute BrandSearch search) {
		
		//Clears the searched list every time the search page is loaded
		searchedList.emptySearchedList();
		
		/* If nothing is entered into the search textbox, all added sunglasses 
		 * populate the searched list. */
		if(search.getBrandSearch().isBlank()) {
			
			for(int i = 0; i < sunglassesList.getSunglassesList().size(); i++) {
				
				searchedList.getSearchedSunglassesList().add(sunglassesList.getSunglassesList().get(i));	
			}
			
		}
		
		/* Iteration through the sunglasses list to compare the entered search with the brand attribute of 
		 * each pair of sunglasses. If matched the searched list is populated with the matching sunglasses.*/
		for(int i = 0; i < sunglassesList.getSunglassesList().size(); i++) {
			
		if(search.getBrandSearch().equalsIgnoreCase(sunglassesList.getSunglassesList().get(i).getBrand())){
			
			searchedList.getSearchedSunglassesList().add(sunglassesList.getSunglassesList().get(i));
			
			} 
		
		}
		
		//the newly populated searched list is added to the model
		model.addAttribute("searchedList", searchedList.getSearchedSunglassesList());

				
		
		return "searchResults";
	}
	
	
	//Handles the HTTP POST request matched with 'addSunglass' url expression
	@PostMapping("/addSunglass")
	public String addSunglass(Model model, @ModelAttribute Sunglasses sunglasses){
		
		//the newly created sunglasses are added to the sunglasses list
		sunglassesList.getSunglassesList().add(sunglasses);
		
		//a new and empty Sunglasses object is added to the model
		model.addAttribute("sunglasses", new Sunglasses());
		
		//the updated sunglass list is added to the model
		model.addAttribute("sunglassesList", sunglassesList.getSunglassesList());
		
		return "display";
	}

	
	
}
