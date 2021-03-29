package com.hardik.flash.service;

import org.springframework.stereotype.Service;

import com.hardik.flash.repository.CompanyRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CompanyService {
	
	private final CompanyRepository companyRepository;

}
