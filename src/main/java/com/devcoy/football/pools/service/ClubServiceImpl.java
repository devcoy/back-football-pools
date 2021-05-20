package com.devcoy.football.pools.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devcoy.football.pools.model.Club;
import com.devcoy.football.pools.repo.ClubRepo;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	ClubRepo clubRepo;

	@Override
	public List<Club> findAll() {

		return this.clubRepo.findAll();
	}

	@Override
	public Page<Club> findAll(Pageable pageable) {

		return this.clubRepo.findAll(pageable);
	}

	@Override
	public Optional<Club> findById(Long id) {

		return this.clubRepo.findById(id);
	}

	@Override
	public Club save(Club club) {

		return this.clubRepo.save(club);
	}

	@Override
	public void delete(Long id) {

		this.clubRepo.deleteById(id);
	}

}
