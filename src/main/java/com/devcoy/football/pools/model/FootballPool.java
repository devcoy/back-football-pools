package com.devcoy.football.pools.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

enum Bet {
	LOCAL, VISIT, TIE
}

@Entity
@Table(name = "football_pools")
public class FootballPool implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Un Partido puede tener MUCHAS Apuestas,
	// Una Apuesta puede tener solo UN Partido
	@JsonIgnoreProperties(value = { "footballPools", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id")
	private Match match;

	/**
	 * Un Usuario puede tener MUCHAS Apuestas, Una Apuesta puede tener UN solo
	 * Usuario
	 */
	@JsonIgnoreProperties(value = { "footballPools", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String bet;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBet() {
		return bet;
	}

	public void setBet(String bet) {
		this.bet = bet;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
