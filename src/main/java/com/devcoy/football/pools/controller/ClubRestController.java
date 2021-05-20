package com.devcoy.football.pools.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devcoy.football.pools.exception.ExceptionResponse;
import com.devcoy.football.pools.exception.TypeException;
import com.devcoy.football.pools.model.Club;
import com.devcoy.football.pools.response.HttpResponse;
import com.devcoy.football.pools.response.TypeStatus;
import com.devcoy.football.pools.service.ClubService;
import com.devcoy.football.pools.service.UploadService;

@RestController
@RequestMapping("/api/club")
public class ClubRestController {

	@Autowired
	ClubService clubService;

	@Autowired
	UploadService uploadService;

	@GetMapping("/all")
	public ResponseEntity<?> index() {

		List<Club> clubs = this.clubService.findAll();

		if (clubs.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NO_CONTENT, null);
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

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Club club) {
		Club updateClub = null;
		Optional<Club> clubOpt = this.clubService.findById(id);

		if (clubOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		Club clubDb = clubOpt.get();
		clubDb.setName(club.getName());
		
		try {
			updateClub = this.clubService.save(clubDb);
		} catch (DataAccessException e) {
			return ExceptionResponse.buildHttpResponse(TypeException.DB_EXCEPTION,
					e.getMessage().concat(": ").toUpperCase().concat(e.getMostSpecificCause().getMessage()));
		}	

		return HttpResponse.buildHttpResponse(TypeStatus.UPDATED, updateClub);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Club> clubOpt = this.clubService.findById(id);

		if (clubOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		// Comprobamos si el club tiene una img asignada
		String imgNameDb = clubOpt.get().getImg();
		this.uploadService.delete(imgNameDb);

		this.clubService.delete(id);
		return HttpResponse.buildHttpResponse(TypeStatus.DELETED, null);
	}

	// IMG
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Long id) {

		Optional<Club> clubOpt = this.clubService.findById(id);

		if (clubOpt.isEmpty()) {
			return HttpResponse.buildHttpResponse(TypeStatus.NOT_FOUND, null);
		}

		if (!file.isEmpty()) {

			String fileName = null;

			try {
				fileName = uploadService.save(file);
			} catch (IOException e) {
				return ExceptionResponse.buildHttpResponse(TypeException.UPLOAD_FILE_EXCEPTION, e);
			}

			// Comprobamos si el club tiene una img asignada
			String imgNameDb = clubOpt.get().getImg();
			this.uploadService.delete(imgNameDb);

			clubOpt.get().setImg(fileName);
			this.clubService.save(clubOpt.get());
		}
		return HttpResponse.buildHttpResponse(TypeStatus.FILE_UPLOADED, clubOpt);
	}

	@GetMapping("upload/{fileName}")
	public ResponseEntity<Resource> getImg(@PathVariable String fileName) {

		Resource resource = null;

		try {
			resource = this.uploadService.load(fileName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}

}
