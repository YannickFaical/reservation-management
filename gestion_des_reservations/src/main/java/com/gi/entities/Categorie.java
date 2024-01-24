package com.gi.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="categorie")
public class Categorie {

	@Id
	@Column(name="id_categorie")
	private int idCategorie;
	@Column(name="nom_categorie",length=15)
	private String nomCategorie;
	private float prix;
	@OneToMany(mappedBy="categorie")
	private List<Chambre> chambres;
	public int getIdCategorie() {
		return idCategorie;
	}
	public void setIdCategorie(int idCategorie) {
		this.idCategorie = idCategorie;
	}
	public String getNomCategorie() {
		return nomCategorie;
	}
	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public List<Chambre> getChambres() {
		return chambres;
	}
	public void setChambres(List<Chambre> chambres) {
		this.chambres = chambres;
	}
	public Categorie(int idCategorie, String nomCategorie, float prix, List<Chambre> chambres) {
		super();
		this.idCategorie = idCategorie;
		this.nomCategorie = nomCategorie;
		this.prix = prix;
		this.chambres = chambres;
	}
	public Categorie() {
		super();
	}
	
}
