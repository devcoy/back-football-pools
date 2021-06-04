package com.devcoy.football.pools.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
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
import com.devcoy.football.pools.model.Championship;
import com.devcoy.football.pools.response.HttpResponse;
import com.devcoy.football.pools.response.TypeStatus;
import com.devcoy.football.pools.service.ChampionshipService;

@RestController
@RequestMapping("/api/championship")
public class ChampionshipRestController {

	@Autowired
	ChampionshipService championshipService;

	// Definimos los roles que tendrán acceso al endpoint
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping
	public ResponseEntity<?> index() {
		List<Championship> championships = this.championshipService.findAll();

		if (championships.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NO_CONTENT, championships);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, championships);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {

		Optional<Championship> championshipOpt = this.championshipService.findById(id);

		if (championshipOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, championshipOpt);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, championshipOpt.get());
	}

	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Championship championship, BindingResult result) {
		Championship newChampionship = null;

		if (result.hasErrors()) {
			return validate(result);
		}

		try {
			newChampionship = this.championshipService.save(championship);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.CREATED, newChampionship);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@Valid  @RequestBody Championship championship, BindingResult result, @PathVariable Long id) {
		Championship updateChampionship = null;
		Optional<Championship> championshipOpt = this.championshipService.findById(id);

		if (result.hasErrors()) {
			return validate(result);
		}
		
		if (championshipOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, championshipOpt);
		}

		Championship championshipDb = championshipOpt.get();
		championshipDb.setName(championship.getName());

		try {
			updateChampionship = this.championshipService.save(championshipDb);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.UPDATED, updateChampionship);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {

		Optional<Championship> championshipOpt = this.championshipService.findById(id);

		if (championshipOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, championshipOpt);
		}

		this.championshipService.delete(id);
		return HttpResponse.buildHttpResponse(TypeStatus.DELETED, null);
	}

	// Method to valid
	protected ResponseEntity<?> validate(BindingResult result) {
		Map<String, Object> errors = new HashMap<String, Object>();

		result.getFieldErrors().forEach(err -> {
			errors.put(err.getField(), "El campo '" + err.getField() + "' " + err.getDefaultMessage());
		});

		return ExceptionResponse.buildHttpResponse(TypeException.VALIDATION, errors);
	}

}
