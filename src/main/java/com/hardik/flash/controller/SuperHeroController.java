package com.hardik.flash.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hardik.flash.entity.SuperHero;
import com.hardik.flash.request.SuperHeroCreationRequest;
import com.hardik.flash.service.SuperHeroService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class SuperHeroController {

	private final SuperHeroService superHeroService;

	@PostMapping("v1/super-hero")
	public void superHeroCreationhandler(
			@RequestBody(required = true) final SuperHeroCreationRequest superHeroCreationRequest) {
		superHeroService.create(superHeroCreationRequest);
	}

	@GetMapping("v1/super-heroes")
	public List<SuperHero> superHeroesReteivalHandler() {
		return superHeroService.getSuperHeroes();
	}
}
