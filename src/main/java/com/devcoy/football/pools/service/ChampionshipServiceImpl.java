package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcoy.football.pools.model.Championship;
import com.devcoy.football.pools.repo.ChampionshipRepo;

@Service
public class ChampionshipServiceImpl implements ChampionshipService {

	@Autowired
	ChampionshipRepo championshipRepo;

	@Override
	public List<Championship> findAll() {
		return this.championshipRepo.findAll();
	}

	@Override
	public Pageable findAll(Pageable pageable) {
		return (Pageable) this.championshipRepo.findAll(pageable);
	}

	@Override
	public Optional<Championship> findById(Long id) {
		return this.championshipRepo.findById(id);
	}

	@Override
	public Championship save(Championship championship) {
		return this.championshipRepo.save(championship);
	}

	@Override
	public void delete(Long id) {
		this.championshipRepo.deleteById(id);

	}

}
