package com.devcoy.football.pools.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matches")
public class Match implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@JsonIgnoreProperties(value = { "matches", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soccer_day_id")
	private SoccerDay soccerDay;

	/**
	 * Una Apuesta puede tener UN solo Partido Un Partido puede tener MUCHAS
	 * Apuestas
	 */
	@JsonIgnoreProperties(value = { "match" }, allowSetters = true)
	@OneToMany(mappedBy = "match", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private java.util.List<FootballPool> footballPools;

	@NotNull
	private Club localClub;

	@NotNull
	private Club visitClub;

	@NotNull
	private Date datetime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SoccerDay getSoccerDay() {
		return soccerDay;
	}

	public void setSoccerDay(SoccerDay soccerDay) {
		this.soccerDay = soccerDay;
	}

	public Club getLocalClub() {
		return localClub;
	}

	public void setLocalClub(Club localClub) {
		this.localClub = localClub;
	}

	public Club getVisitClub() {
		return visitClub;
	}

	public void setVisitClub(Club visitClub) {
		this.visitClub = visitClub;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public java.util.List<FootballPool> getFootballPools() {
		return footballPools;
	}

	public void setFootballPools(java.util.List<FootballPool> footballPools) {
		this.footballPools.clear();

		/**
		 * Por cada Apuesta, debemos setear el Partido
		 */
		this.footballPools.forEach(footballPool -> this.addFootballPool(footballPool));

		this.footballPools = footballPools;
	}

	public void addFootballPool(FootballPool footballPool) {
		this.footballPools.add(footballPool);
		footballPool.setMatch(this);
	}

	public void removeFootballPool(FootballPool footballPool) {
		this.footballPools.remove(footballPool);
		footballPool.setMatch(null);

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Match)) {
			return false;
		}
		Match matchCast = (Match) obj;
		return this.id != null && this.id.equals(matchCast.getId());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
