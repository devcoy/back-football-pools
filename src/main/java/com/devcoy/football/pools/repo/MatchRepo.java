package com.devcoy.football.pools.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcoy.football.pools.model.Match;

public interface MatchRepo extends JpaRepository<Match, Long> {

}
