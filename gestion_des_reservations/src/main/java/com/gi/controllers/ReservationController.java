package com.gi.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gi.entities.Chambre;
import com.gi.entities.Client;
import com.gi.entities.Reservation;
import com.gi.entities.ReservationCle;
import com.gi.repositories.ChambreRepository;
import com.gi.repositories.ClientRepository;
import com.gi.repositories.ReservationRepository;

@Controller
public class ReservationController {
	@Autowired
	private ReservationRepository reservationRep;
	@Autowired
	private ChambreRepository chambreRepo;
	@Autowired
	private ClientRepository clientRepo;
	@GetMapping("/")
	public String afficherListeReservations(Model model) {
		List<Reservation> reservations=reservationRep.findAll();
		model.addAttribute("reservations", reservations);
		return "index";
	}
	
	@GetMapping("/ajoutReservation")
	public String ajouterReservation(Model model) {
		
		List<Chambre> chambres=chambreRepo.findAll();
		List<Client> clients=clientRepo.findAll();
		model.addAttribute("clients", clients);
		model.addAttribute("chambres", chambres);
		return "formAjoutReservation";
	}
	
	@PostMapping("/saveReservation")
	public String sauvegarderReservation(@RequestParam int noChambre, @RequestParam int idClient, @RequestParam Date dateReservation, @RequestParam int duree) {
		@SuppressWarnings("deprecation")
		Chambre chambre=chambreRepo.getById(noChambre);
		Client client=clientRepo.getById(idClient);
		Reservation newReservation=new Reservation();
		ReservationCle idReservation=new ReservationCle();
		idReservation.setIdClient(idClient);
		idReservation.setNoChambre(noChambre);
		newReservation.setDateReservation(dateReservation);
		newReservation.setDuree(duree);
		newReservation.setIdReservation(idReservation);
		newReservation.setChambre(chambre);
		newReservation.setClient(client);
		reservationRep.save(newReservation);
		return "redirect:/";
	}
	
	@GetMapping("/formFacture")
	public String formEtablirFacture(Model model) {
		List<Client> clients=clientRepo.findAll();
		model.addAttribute("clients", clients);
		return "formEtablirFacture";
	}
	@PostMapping("/etablirFacture")
	public String etablirFacture(@RequestParam int idClient,Model model) {
		float s=0;
		int i;
		Client client=clientRepo.getById(idClient);
		model.addAttribute("client", client);
		List<Reservation> reservations=reservationRep.findAllByIdClient(idClient);
		for(i=0;i<reservations.size();i++)
			s=s+reservations.get(i).getChambre().getCategorie().getPrix()*reservations.get(i).getDuree();
		model.addAttribute("s", s);
		model.addAttribute("reservations", reservations);
		return "facture";
	}
	
	@GetMapping("/editerReservation")
	public String editerReservation(@RequestParam int noChambre, @RequestParam int idClient, Model model) {
		Reservation reservation=new Reservation();
		ReservationCle idReservation=new ReservationCle();
		idReservation.setIdClient(idClient);
		idReservation.setNoChambre(noChambre);
		Client client=clientRepo.getById(idClient);
		Chambre chambre=chambreRepo.getById(noChambre);
		reservation.setChambre(chambre);
		reservation.setClient(client);
		reservation=reservationRep.getById(idReservation);
		model.addAttribute("reservation", reservation);
		return "formEditReservation";
	}
	
	@PostMapping("/updateReservation")
	public String modifierReservation(@RequestParam int noChambre, @RequestParam int idClient, @RequestParam Date dateReservation, @RequestParam int duree) {
		
		reservationRep.mettreAJourReservation(idClient, noChambre, duree, dateReservation);
		return "redirect:/";
	}
	
	@GetMapping("/deleteReservation")
	public String supprimerReservation(@RequestParam int noChambre, @RequestParam int idClient) {
		
		ReservationCle idReservation=new ReservationCle();
		idReservation.setIdClient(idClient);
		idReservation.setNoChambre(noChambre);
		reservationRep.deleteById(idReservation);
		return "redirect:/";
	}

}
