package com.gi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gi.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query("select c from Client c where c.nom like %:motcle%")
	public List<Client> findAllByMotCle(@Param("motcle") String motcle);
	
	public Client findAllByIdClient(@Param("idClient") int idClient);
	
	@Transactional
	@Modifying
	@Query("update Client c set c.nom=:nom, c.prenom=:prenom, c.email=:email, c.tel=:tel where c.idClient=:idClient")
	public void mettreAJourClient(@Param("idClient") int idClient, @Param("nom") String nom, @Param("prenom") String prenom, @Param("email") String email, @Param("tel") String tel);

}
