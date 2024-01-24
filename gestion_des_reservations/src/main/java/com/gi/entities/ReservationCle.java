package com.gi.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReservationCle {
	
	private int idClient;
	private int noChambre;
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getNoChambre() {
		return noChambre;
	}
	public void setNoChambre(int noChambre) {
		this.noChambre = noChambre;
	}
	public ReservationCle(int idClient, int noChambre) {
		super();
		this.idClient = idClient;
		this.noChambre = noChambre;
	}
	public ReservationCle() {
		super();
	}
	
	

}
