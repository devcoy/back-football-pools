package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.devcoy.football.pools.model.SoccerDay;

public interface SoccerDayService {

	public List<SoccerDay> findAll();

	public Page<SoccerDay> findAll(Pageable pageable);

	public Optional<SoccerDay> findById(Long id);

	public SoccerDay save(SoccerDay soccerDay);

	public void delete(Long id);

}
