package com.devcoy.football.pools.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "soccer_days")
public class SoccerDay implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String name;

	private Date startDate;

	private Date endDate;

	/*
	 * Está class (SoccerDay) es la dueña de la relación, esto lo sabemos porque
	 * tiene el "@JoinColumn",
	 * 
	 * @JsonIgnoreProperties: hace que no se una loop infinito, ya que cada examen
	 * tendrá preguntas anidado
	 */
	@JsonIgnoreProperties(value = { "soccerDays", "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "championship_id")
	private Championship championship;

	@JsonIgnoreProperties(value = { "soccerDay" }, allowSetters = true)
	@OneToMany(mappedBy = "soccerDay", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Match> matches;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Championship getChampionship() {
		return championship;
	}

	public void setChampionship(Championship championship) {
		this.championship = championship;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches.clear();

		this.matches.forEach(question -> this.addMatch(question));
	}

	public void addMatch(Match match) {
		this.matches.add(match);
		match.setSoccerDay(this);
	}

	public void removeMatch(Match match) {
		this.matches.remove(match);
		match.setSoccerDay(null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SoccerDay)) {
			return false;
		}
		SoccerDay soccerDayCast = (SoccerDay) obj;
		return this.id != null && this.id.equals(soccerDayCast.getId());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
