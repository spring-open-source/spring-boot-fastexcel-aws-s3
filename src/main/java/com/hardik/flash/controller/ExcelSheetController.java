package com.hardik.flash.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.flash.dto.FileSuccessDto;
import com.hardik.flash.service.ExcelService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ExcelSheetController {

	private final ExcelService excelService;

	@GetMapping("v1/generate-excel")
	public FileSuccessDto excelSheetGenerationHandler() {
		return excelService.generate();
	}
}
