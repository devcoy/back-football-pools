package com.devcoy.football.pools.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "matches")
public class Match implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties(value = {"matches", "hibernateLazyInitializer", "handler"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "soccer_day_id")
	private SoccerDay soccerDay;

	private Club localClub;

	private Club visitClub;

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
