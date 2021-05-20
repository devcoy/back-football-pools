package com.devcoy.football.pools.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

@Entity
@Table(name = "championships")
public class Championship implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String name;

	@JsonIgnoreProperties(value = { "championship" }, allowSetters = true)
	@OneToMany(mappedBy = "championship", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SoccerDay> soccerDays;

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

	public List<SoccerDay> getSoccerDays() {
		return soccerDays;
	}

	public void setSoccerDays(List<SoccerDay> soccerDays) {
		this.soccerDays.clear();

		/**
		 * Por cada Joranda, debemos setear el Torneo
		 */
		this.soccerDays.forEach(soccerDay -> this.addSoccerDay(soccerDay));
		this.soccerDays = soccerDays;
	}

	public void addSoccerDay(SoccerDay soccerDay) {
		this.soccerDays.add(soccerDay);
		soccerDay.setChampionship(this);
	}

	public void removeSoccerDay(SoccerDay soccerDay) {
		this.soccerDays.remove(soccerDay);

		soccerDay.setChampionship(null);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Championship)) {
			return false;
		}
		Championship championshipCast = (Championship) obj;
		return this.id != null && this.id.equals(championshipCast.getId());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
