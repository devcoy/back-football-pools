package com.devcoy.football.pools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcoy.football.pools.service.ChampionshipService;

@RestController
@RequestMapping("/api/championship")
public class ChampionshipRestController {

	@Autowired
	ChampionshipService championshipService;

	@GetMapping
	public ResponseEntity<?> index() {
		return null;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById() {
		return null;
	}
	
	@PostMapping
	public ResponseEntity<?> create() {
		return null;
	}
	
	@PutMapping("/{id}") 
	public ResponseEntity<?> update() {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(){
		return null;
	}

}
