package com.gi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gi.entities.Client;
import com.gi.repositories.ClientRepository;

@Controller
public class ClientController {
	@Autowired
	private ClientRepository cltRepo;
	
	@GetMapping("/clients")
	public String afficheClients(@RequestParam(defaultValue="") String motcle,Model model) {
		
		List<Client> clients;
		if(motcle.equals(""))
			clients=cltRepo.findAll();
		else
			clients=cltRepo.findAllByMotCle(motcle);
		model.addAttribute("clients", clients);
		return "vueClients";
	}

	@GetMapping("/ajoutClient")
	public String ajouterClient() {
		
		return "formAjoutClient";
	}
	
	@PostMapping("/saveClient")
	public String saveClient(@RequestParam int idClient, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String tel ) {
		
		Client newClient=new Client();
		newClient.setIdClient(idClient);
		newClient.setNom(nom);
		newClient.setPrenom(prenom);
		newClient.setEmail(email);
		newClient.setTel(tel);
		cltRepo.save(newClient);
		return "redirect:/clients";
	}
	
	@GetMapping("/editerClient")
	public String editerClient(@RequestParam int idClient,Model model) {
		Client client=cltRepo.findAllByIdClient(idClient);
		model.addAttribute("client", client);
		return "formEditClient";
	}
	
	@PostMapping("/updateClient")
	public String modifierClient(@RequestParam int idClient, @RequestParam String nom, @RequestParam String prenom, @RequestParam String email, @RequestParam String tel) {
		
		cltRepo.mettreAJourClient(idClient, nom, prenom, email, tel);
		return "redirect:/clients";
	}
	
	@GetMapping("/supprimerClient")
	public String supprimerClient(@RequestParam int idClient) {
		cltRepo.deleteById(idClient);
		return "redirect:/clients";
	}
}
