package com.devcoy.football.pools.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcoy.football.pools.model.Match;
import com.devcoy.football.pools.repo.MatchRepo;

@Service
public class MatchServiceImpl implements MatchService {

	@Autowired
	MatchRepo matchRepo;

	@Override
	public Page<Match> findAll(Pageable pageable) {
		return this.matchRepo.findAll(pageable);
	}

	@Override
	public Optional<Match> findById(Long id) {

		return this.matchRepo.findById(id);
	}

	@Override
	public Match save(Match match) {

		return this.matchRepo.save(match);
	}

	@Override
	public void delete(Long id) {

		this.matchRepo.deleteById(id);
	}

}
