package com.nat.produits.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   //Génère automatiquement les getters et setters avec lombok
@NoArgsConstructor   //Génèreautomatiquement un constructeur sans argument
@AllArgsConstructor // Génère automatiquement de façon caché un constructeur avec tous les attributs
@Entity
public class Categorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCat;
	private String nomCat;
	private String descritionCat;
	
	//mappedBy permet de lié la class Categorie à la classe Produit via l'attribut categorie
	//Il y a un problème de de réféence croisée qui a été posé lorque Spring les produits, il va aller dans l'entité produit et l'entité catégorie
	//Cela cause un problème de sérialisation 
	//Il faut demander  à Spring de ne pas retourner la liste des produits pour une catégorie. c'est à dire que lorsque je veux des produits,
	//je ne veux pas la liste des produits qui sont dans catégorie, pour éviter cette boucle infini. L'annoation ci-dessous permet cela
	
	@JsonIgnore
	@OneToMany(mappedBy = "categorie")
	private List<Produit> produits;

}
