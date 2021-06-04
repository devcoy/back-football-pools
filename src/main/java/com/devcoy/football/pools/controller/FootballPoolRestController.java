package com.devcoy.football.pools.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcoy.football.pools.exception.ExceptionResponse;
import com.devcoy.football.pools.exception.TypeException;
import com.devcoy.football.pools.model.FootballPool;
import com.devcoy.football.pools.response.HttpResponse;
import com.devcoy.football.pools.response.TypeStatus;
import com.devcoy.football.pools.service.FootballPoolService;

@RestController
@RequestMapping("/api/football-pool")
public class FootballPoolRestController {

	@Autowired
	private FootballPoolService footballPoolService;

	@GetMapping("/all")
	public ResponseEntity<?> index() {
		List<FootballPool> footballPools = this.footballPoolService.findAll();

		if (footballPools.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NO_CONTENT, footballPools);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, footballPools);
	}

	@GetMapping()
	public ResponseEntity<?> index(Pageable pageable) {
		Page<?> footballPoolsPage = this.footballPoolService.findAll(pageable);

		return HttpResponse.buildHttpResponse(TypeStatus.READED, footballPoolsPage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {
		Optional<FootballPool> footballPoolOpt = this.footballPoolService.findById(id);

		if (footballPoolOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, footballPoolOpt);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody FootballPool footballPool) {
		FootballPool newFootballPool = null;

		try {
			newFootballPool = this.footballPoolService.save(newFootballPool);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.CREATED, newFootballPool);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody FootballPool footballPool, @PathVariable Long id) {

		FootballPool updateteFootballPool = null;
		Optional<FootballPool> footballPoolOpt = this.footballPoolService.findById(id);

		if (footballPoolOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		FootballPool footballPoolDb = footballPoolOpt.get();
		footballPoolDb.setMatch(footballPool.getMatch());
		footballPoolDb.setUser(footballPool.getUser());
		footballPoolDb.setBet(footballPool.getBet());

		try {
			updateteFootballPool = this.footballPoolService.save(footballPoolDb);

		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.UPDATED, updateteFootballPool);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<FootballPool> footballPoolOpt = this.footballPoolService.findById(id);

		if (footballPoolOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		this.footballPoolService.delete(id);
		return HttpResponse.buildHttpResponse(TypeStatus.DELETED, null);
	}

}
