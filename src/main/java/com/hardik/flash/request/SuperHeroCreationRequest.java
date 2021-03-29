package com.hardik.flash.request;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class SuperHeroCreationRequest {
	
	private final String name;
	private final String alterEgo;
	private final Integer companyId;

}
