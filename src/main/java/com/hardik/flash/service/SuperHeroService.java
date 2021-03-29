package com.hardik.flash.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hardik.flash.entity.SuperHero;
import com.hardik.flash.repository.SuperHeroRepository;
import com.hardik.flash.request.SuperHeroCreationRequest;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuperHeroService {
	
	private final SuperHeroRepository superHeroRepository;

	public void create(SuperHeroCreationRequest superHeroCreationRequest) {
		final var superHero = new SuperHero();
		superHero.setName(superHeroCreationRequest.getName());
		superHero.setAlterEgo(superHeroCreationRequest.getAlterEgo());
		superHero.setCompanyId(superHeroCreationRequest.getCompanyId());
		superHeroRepository.save(superHero);
	}

	public List<SuperHero> getSuperHeroes() {
		return superHeroRepository.findAll();
	}

}
