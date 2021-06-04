package com.devcoy.football.pools.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	/**
	 * Un Partido puede tener muchas Apuestas, Una Apuesta puede tener solo UN
	 * Partido
	 */
	@JsonIgnoreProperties(value = { "footballPools", "hibernateLazyInitializer", "handler" })
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "match_id")
	private Match match;

	/**
	 * Un Usuario puede tener MUCHAS Apuestas, Una Apuesta puede tener UN solo
	 * Usuario
	 */
	@JsonIgnoreProperties(value = { "FootballPools", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private Bet bet;

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

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
