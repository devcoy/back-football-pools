package com.devcoy.football.pools.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.devcoy.football.pools.model.Club;
import com.devcoy.football.pools.response.HttpResponse;
import com.devcoy.football.pools.response.TypeStatus;
import com.devcoy.football.pools.service.ClubService;

@RestController
@RequestMapping("/api/club")
public class ClubRestController {

	@Autowired
	ClubService clubService;

	@GetMapping("/all")
	public ResponseEntity<?> index() {

		List<Club> clubs = this.clubService.findAll();

		if (clubs.isEmpty()) {
			HttpResponse.buildHttpResponse(TypeStatus.NO_CONTENT, clubs);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, clubs);
	}

	@GetMapping
	public Page<?> index(@PathVariable Integer page) {

		Pageable pageable = PageRequest.of(page, 4);
		return this.clubService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id) {

		Optional<Club> clubOpt = this.clubService.findById(id);

		if (clubOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.READED, clubOpt.get());
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Club club) {

		Club newClub = null;

		try {
			newClub = this.clubService.save(club);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.CREATED, newClub);
	}

	@PutMapping
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Club club) {
		Club updateClub = null;
		Optional<Club> clubOpt = this.clubService.findById(id);

		if (clubOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		try {
			updateClub = this.clubService.save(club);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}

		return HttpResponse.buildHttpResponse(TypeStatus.UPDATED, updateClub);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Club> clubOpt = this.clubService.findById(id);

		if (clubOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		return HttpResponse.buildHttpResponse(TypeStatus.DELETED, null);
	}

}
