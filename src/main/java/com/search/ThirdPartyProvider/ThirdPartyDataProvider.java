package com.search.ThirdPartyProvider;

import com.search.ThirdPartyProvider.model.ThirdPartyRequest;
import com.search.ThirdPartyProvider.model.ThirdPartyResponse;

public interface ThirdPartyDataProvider {
	
	ThirdPartyResponse searchData(ThirdPartyRequest request);
}
