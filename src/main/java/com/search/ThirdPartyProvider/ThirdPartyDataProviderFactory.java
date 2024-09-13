package com.search.ThirdPartyProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ThirdPartyDataProviderFactory {
	
	@Autowired
	@Qualifier("provider1")
	private static ThirdPartyDataProvider provider1;
	
	@Autowired
	@Qualifier("provider2")
	private static ThirdPartyDataProvider provider2;

	
	 
	public static ThirdPartyDataProvider getProvider(String providerName) {
		
		switch(providerName) {
		
		   case "Provider1": 
		     return provider1;
		   case "Provider2":
			  return provider2;
		   default: return provider1;
	    }
	}

}

