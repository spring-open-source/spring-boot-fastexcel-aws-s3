package com.hardik.flash.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.flash.entity.SuperHero;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, UUID>{

}
