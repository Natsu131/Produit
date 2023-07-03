package com.nat.produits.dto;


import java.util.Date;

import com.nat.produits.entities.Categorie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor //Génère Construcetur par default (sans argument)
@AllArgsConstructor // Génère Construcetur avec tous les arguments
@Builder // Permet d'utiliser le design pattern Builder, qui permet de construire plus facilement des objets 

//L'une des avantages des DTO, ce qu'elles permettent de masquer certains attributs aux clients, par exemple je peux enlever l'attrobut prix. Je peux également ajouter des nouveaux champs
public class ProduitDTO {
	private Long idProduit;
	private String nomProduit;
	private Double prixProduit;
	private Date dateCreation;
	private Categorie categorie;

}
