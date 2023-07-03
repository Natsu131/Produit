package com.nat.produits.service;

import java.util.List;

import com.nat.produits.dto.ProduitDTO;
import com.nat.produits.entities.Categorie;
import com.nat.produits.entities.Produit;

public interface ProduitService {
	
	/*Modifier les méthodes de ProduitService pour retourner des DTO*/
	
	ProduitDTO saveProduit (ProduitDTO p);
	ProduitDTO getProduit(Long id);
	List<ProduitDTO> getAllProduits();
	
	ProduitDTO updateProduit(ProduitDTO p);
	void deleteProduit(Produit P);
	void deleteProduitById(Long id);
	
	
	
	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	List<Produit> findByNomPrix (String nom, Double prix);
	List<Produit> findByCategorie (Categorie categorie);
	List<Produit> findByCategorieIdCat(Long id);
	List<Produit> findByOrderByNomProduitAsc();
	List<Produit> trierProduitsNomsPrix();
	
	ProduitDTO convertEntityToDTO(Produit p);
	Produit convertDtoToEntity(ProduitDTO produitDTO);
}
