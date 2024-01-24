package com.gi.entities;

import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name="reservation")
public class Reservation {

	@EmbeddedId
	private ReservationCle idReservation;
	private int duree;
	private Date dateReservation;
	@ManyToOne
	@MapsId("noChambre")
	@JoinColumn(name="no_chambre")
	private Chambre chambre;
	
	@ManyToOne
	@MapsId("idClient")
	@JoinColumn(name="id_client")
	private Client client;

	public ReservationCle getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(ReservationCle idReservation) {
		this.idReservation = idReservation;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public Chambre getChambre() {
		return chambre;
	}

	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Reservation(ReservationCle idReservation, int duree, Date dateReservation, Chambre chambre, Client client) {
		super();
		this.idReservation = idReservation;
		this.duree = duree;
		this.dateReservation = dateReservation;
		this.chambre = chambre;
		this.client = client;
	}

	public Reservation() {
		super();
	}
	
}
