package com.devcoy.football.pools.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcoy.football.pools.model.Championship;

public interface ChampionshipRepo extends JpaRepository<Championship, Long> {

}
