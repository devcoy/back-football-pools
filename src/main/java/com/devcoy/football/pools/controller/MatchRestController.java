package com.devcoy.football.pools.controller;

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
import com.devcoy.football.pools.model.Match;
import com.devcoy.football.pools.response.HttpResponse;
import com.devcoy.football.pools.response.TypeStatus;
import com.devcoy.football.pools.service.MatchService;

@RestController
@RequestMapping("/api/match")
public class MatchRestController {

	@Autowired
	private MatchService matchService;

	@GetMapping
	public ResponseEntity<?> index(Pageable pageable) {
		Page<?> matches = this.matchService.findAll(pageable);

		if (matches.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NO_CONTENT, null);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, matches);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {

		Optional<Match> matchOpt = this.matchService.findById(id);

		if (matchOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, matchOpt.get());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Match match) {

		Match newMatch = null;

		try {
			newMatch = this.matchService.save(match);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.CREATED, newMatch);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestBody Match match, @PathVariable Long id) {

		Match updaMatch = null;
		Optional<Match> matchOpt = this.matchService.findById(id);

		if (matchOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		Match matchDb = matchOpt.get();
		matchDb.setLocalClub(match.getLocalClub());
		matchDb.setVisitClub(match.getVisitClub());
		matchDb.setDatetime(match.getDatetime());
		matchDb.setSoccerDay(match.getSoccerDay());

		try {
			updaMatch = this.matchService.save(matchDb);

		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.UPDATED, updaMatch);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Optional<Match> matchOpt = this.matchService.findById(id);

		if (matchOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		this.matchService.delete(id);
		return HttpResponse.buildHttpResponse(TypeStatus.DELETED, null);
	}
}
