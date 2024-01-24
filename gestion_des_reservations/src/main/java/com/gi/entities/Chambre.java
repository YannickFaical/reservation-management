package com.gi.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="chambre")
public class Chambre {
	
	@Id
	@Column(name="no_chambre")
	private int noChambre;
	@Column(name="tel_chambre")
	private int telChambre;
	
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categorie;
	@OneToMany(mappedBy="chambre")
	private List<Reservation> reservations;
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public int getNoChambre() {
		return noChambre;
	}

	public void setNoChambre(int noChambre) {
		this.noChambre = noChambre;
	}

	public int getTelChambre() {
		return telChambre;
	}

	public void setTelChambre(int telChambre) {
		this.telChambre = telChambre;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	
	public Chambre(int noChambre, int telChambre, Categorie categorie, List<Reservation> reservations) {
		super();
		this.noChambre = noChambre;
		this.telChambre = telChambre;
		this.categorie = categorie;
		this.reservations = reservations;
	}

	public Chambre() {
		super();
	}
	
	

}
