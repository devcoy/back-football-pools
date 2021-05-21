package com.devcoy.football.pools.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcoy.football.pools.model.Match;

public interface MatchService {

	public Page<Match> findAll(Pageable pageable);

	public Optional<Match> findById(Long id);

	public Match save(Match match);

	public void delete(Long id);

}
