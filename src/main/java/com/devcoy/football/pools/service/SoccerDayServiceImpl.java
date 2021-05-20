package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcoy.football.pools.model.SoccerDay;
import com.devcoy.football.pools.repo.SoccerDayRepo;

@Service
public class SoccerDayServiceImpl implements SoccerDayService {

	@Autowired
	SoccerDayRepo soccerDayRepo;

	@Override
	public List<SoccerDay> findAll() {

		return this.soccerDayRepo.findAll();
	}

	@Override
	public Page<SoccerDay> findAll(Pageable pageable) {

		return this.soccerDayRepo.findAll(pageable);
	}

	@Override
	public Optional<SoccerDay> findById(Long id) {

		return this.soccerDayRepo.findById(id);
	}

	@Override
	public SoccerDay save(SoccerDay soccerDay) {

		return this.soccerDayRepo.save(soccerDay);
	}

	@Override
	public void delete(Long id) {

		this.soccerDayRepo.deleteById(id);
	}

}
