package com.devcoy.football.pools.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fullname;

	@Column(unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	private String password;

	/**
	 * Al usar una realción ManyToMany se crea una tabla intermedia para relacionar
	 * ambas Entidades
	 */
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// Config de tabla intermedia
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "user_id", "role_id" }) })
	private List<Role> roles;

	private Boolean enabled;

	@Column(name = "created_at")
	private Date createdAt;

	/**
	 * Una Apuesta puede tener solo UN Usuario, Un Usuario puede tener MUCHAS
	 * Apuestas
	 */
	@JsonIgnoreProperties(value = { "user" }, allowSetters = true)
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FootballPool> footballPools;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<FootballPool> getFootballPools() {
		return footballPools;
	}

	public void setFootballPools(List<FootballPool> footballPools) {
		this.footballPools.clear();
		/**
		 * Por cada Usuario, debemos setear la Apuesta
		 */
		this.footballPools.forEach(footballPool -> this.addFootballPool(footballPool));
		this.footballPools = footballPools;
	}

	public void addFootballPool(FootballPool footballPool) {
		this.footballPools.add(footballPool);
		footballPool.setUser(this);
	}

	public void removeFootballPool(FootballPool footballPool) {
		this.footballPools.remove(footballPool);
		footballPool.setUser(null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
