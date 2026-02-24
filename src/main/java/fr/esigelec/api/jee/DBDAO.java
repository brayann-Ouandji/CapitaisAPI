package fr.esigelec.api.jee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBDAO {
	private List<String> countryCodes;
	private Map<String, String> countryNames;
	private Map<String, String> capitals;

	public DBDAO() {
		// Initialize the lists and maps
    	countryCodes = new ArrayList<String>();
    	countryNames = new HashMap<>();
    	capitals = new HashMap<>();
    	
    	countryCodes.add("PT");
    	countryCodes.add("CM");
    	countryCodes.add("FR");
    	countryCodes.add("JA");
    	
    	countryNames.put("PT", "Portugal");
    	countryNames.put("CM", "Cameroon");
    	countryNames.put("FR", "France");
    	countryNames.put("JA", "Japon");
    	

    	capitals.put("PT", "Lisbon");
    	capitals.put("CM", "Yaounde");
    	capitals.put("FR", "Paris");
    	capitals.put("JA", "Tokyo");
	}
    	
    	
    	
    	public List<String> getCountryCodes() {
    		return countryCodes;
    	}
    	
    	public String getCountryName(String countryCodes) {
    		return countryNames.get(countryCodes.toUpperCase());
    	}
    	
    	public String getCapitalName(String countryCodes) {
			return capitals.get(countryCodes.toUpperCase());
    }
    	public CountryModel getCountry(String countryCode) {
    		String Code = countryCode.toUpperCase();
    		
            if (countryCode.contains(Code)) {
            	
            	CountryModel countrym = new CountryModel();
                countrym.setCode(Code);
                countrym.setName(countryNames.get(Code));
                countrym.setCapital(capitals.get(Code));
                return countrym;
            }
            return null;
        }
    	
    	public boolean validateUserCredentials(String user, String pass) {
    		
    	}
}
