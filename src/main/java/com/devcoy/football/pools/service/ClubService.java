package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcoy.football.pools.model.Club;

public interface ClubService {

	public List<Club> findAll();
	
	public Page<Club> findAll(Pageable pageable);
	
	Optional<Club> findById(Long id);
	
	public Club save(Club club);
	
	public void delete(Long id);
}
