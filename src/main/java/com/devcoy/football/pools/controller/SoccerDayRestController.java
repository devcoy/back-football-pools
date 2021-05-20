package com.devcoy.football.pools.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
import com.devcoy.football.pools.model.SoccerDay;
import com.devcoy.football.pools.response.HttpResponse;
import com.devcoy.football.pools.response.TypeStatus;
import com.devcoy.football.pools.service.SoccerDayService;

@RestController
@RequestMapping("api/soccer-day")
public class SoccerDayRestController {

	@Autowired
	SoccerDayService soccerDayService;

	@GetMapping
	public ResponseEntity<?> index() {

		List<SoccerDay> soccerDays = this.soccerDayService.findAll();

		if (soccerDays.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NO_CONTENT, soccerDays);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, soccerDays);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {

		Optional<SoccerDay> soccerDayOpt = this.soccerDayService.findById(id);

		if (soccerDayOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, soccerDayOpt);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, soccerDayOpt.get());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody SoccerDay soccerDay) {

		SoccerDay newSoccerDay = null;

		try {
			newSoccerDay = this.soccerDayService.save(soccerDay);

		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.CREATED, newSoccerDay);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody SoccerDay soccerDay) {

		SoccerDay updateSoccerDay = null;
		Optional<SoccerDay> soccerDayOpt = this.soccerDayService.findById(id);

		if (soccerDayOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, soccerDayOpt);
		}

		try {
			updateSoccerDay = this.soccerDayService.save(soccerDay);

		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.UPDATED, updateSoccerDay);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {

		Optional<SoccerDay> soccerDayOpt = this.soccerDayService.findById(id);

		if (soccerDayOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, soccerDayOpt);
		}

		this.soccerDayService.delete(id);

		return HttpResponse.buildHttpResponse(TypeStatus.DELETED, null);

	}

}
