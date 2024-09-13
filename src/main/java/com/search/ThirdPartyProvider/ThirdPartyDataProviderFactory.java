package com.search.ThirdPartyProvider;

import org.springframework.context.ApplicationContext;

public class ThirdPartyDataProviderFactory {
	
	 
	ThirdPartyDataProvider getProvider(String providerName) {
		
		switch(providerName) {
		
		case "Provider1": 
		   return getAppContext()
		}
	}

}

\