package com.hardik.flash.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class FileSuccessDto {
	
	private final Integer status;
	private final String key;
	private final LocalDateTime timestamp; 

}
