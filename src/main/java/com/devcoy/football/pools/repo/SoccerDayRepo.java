package com.devcoy.football.pools.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcoy.football.pools.model.SoccerDay;

public interface SoccerDayRepo extends JpaRepository<SoccerDay, Long> {

}
