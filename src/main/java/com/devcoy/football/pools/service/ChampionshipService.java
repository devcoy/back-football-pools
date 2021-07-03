package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import com.devcoy.football.pools.model.Championship;

public interface ChampionshipService {

	public List<Championship> findAll();

	public org.springframework.data.domain.Pageable findAll(org.springframework.data.domain.Pageable pageable);

	public Optional<Championship> findById(Long id);

	public Championship save(Championship championship);

	public void delete(Long id);

}
