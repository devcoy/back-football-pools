package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcoy.football.pools.model.FootballPool;

public interface FootballPoolService {

	public List<FootballPool> findAll();

	public Page<FootballPool> findAll(Pageable pageable);

	public Optional<FootballPool> findById(Long id);

	public FootballPool save(FootballPool footballPool);

	public void delete(Long id);
}
