package com.devcoy.football.pools.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcoy.football.pools.model.FootballPool;

public interface FootballPoolRepo extends JpaRepository<FootballPool, Long> {

}
