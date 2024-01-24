package com.gi.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.gi.entities.Reservation;
import com.gi.entities.ReservationCle;

public interface ReservationRepository extends JpaRepository<Reservation,ReservationCle>{

	@Query("select r from Reservation r where r.idReservation.idClient=:idClient")
	public List<Reservation> findAllByIdClient(@Param("idClient") int idClient);
	
	@Transactional
	@Modifying
	@Query("update Reservation r set r.duree=:duree, r.dateReservation=:dateReservation where  r.idReservation.idClient=:idClient AND r.idReservation.noChambre=:noChambre")
	public void mettreAJourReservation(@Param("idClient") int idClient, @Param("noChambre") int noChambre, @Param("duree") int duree, @Param("dateReservation") Date dateReservation);
}
