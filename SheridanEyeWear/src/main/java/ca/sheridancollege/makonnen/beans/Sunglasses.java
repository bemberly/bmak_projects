package ca.sheridancollege.makonnen.beans;

import lombok.Data;

import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class Sunglasses {
	
	//All the attributes required for creating a pair of sunglasses.
	@NonNull
	private String productName;
	@NonNull
	private String brand;
	@NonNull
	private String lensType;
	@NonNull
	private String color;
	@NonNull
	private String frameType;
	@NonNull
	private String size;
	@NonNull
	private String price;
	
	//All the constants containing a list of strings used in the form's drop down options
	private final String[] productNames = {"Eye Glasses", "Reading Glasses", "Sunglasses"};
	
	private final String[] prices = {"$45", "$90", "$120", "$260", "$300", "$450", "$600"};
	
	private final String[] brands = {"Ray-Ban", "Gucci", "Chanel", "Versace", "Kenzo", "Armani"};
	
	private final String[] colors = {"Black", "Blue", "Red", "Brown", "Green", "Pink"};
	
	private final String[] sizes = {"49mm", "52mm", "55mm", "57mm", "58mm", "61mm"};
	
	private final String[] lensTypes = {"Polarized", "Glass", "High Index Plastic", "Polycarbonate", "Photochromic"};
	
	private final String[] frameTypes = {"Aviator", "Square", "Round", "Cat Eye", "Browline", "Sport"};

}
