package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcoy.football.pools.model.FootballPool;
import com.devcoy.football.pools.repo.FootballPoolRepo;

@Service
public class FootballPoolServiceImpl implements FootballPoolService {

	@Autowired
	private FootballPoolRepo footballPoolRepo;

	@Override
	public List<FootballPool> findAll() {
		return this.footballPoolRepo.findAll();
	}

	@Override
	public Page<FootballPool> findAll(Pageable pageable) {
		return this.footballPoolRepo.findAll(pageable);
	}

	@Override
	public Optional<FootballPool> findById(Long id) {
		return this.footballPoolRepo.findById(id);
	}

	@Override
	@Transactional
	public FootballPool save(FootballPool footballPool) {
		return this.footballPoolRepo.save(footballPool);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.footballPoolRepo.deleteById(id);
	}

}
