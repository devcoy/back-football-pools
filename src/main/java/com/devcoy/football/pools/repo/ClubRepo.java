package com.devcoy.football.pools.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcoy.football.pools.model.Club;

public interface ClubRepo extends JpaRepository<Club, Long> {

}
