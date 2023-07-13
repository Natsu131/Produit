package com.nat.produits.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nat.produits.dto.ProduitDTO;
import com.nat.produits.entities.Produit;
import com.nat.produits.service.ProduitService;

@RestController // Class qui va contenir des méthodes qui seront des web services, consommer/utiliser par un navigateur 
@RequestMapping ("/api")//Pour accéder aux méthodes web services de cette classe, on doit taper dans l'url le nom de notre site 
//: localhost:8080/produits/api
@CrossOrigin // Permet à toute les adresse de consommer ces web services
public class ProduitRESTController {
	
	@Autowired
	ProduitService produitService;
	
	
	@GetMapping
	
	public List<ProduitDTO> getAllProduits(){
		System.out.println("getMapping method");
		
		return produitService.getAllProduits();
	}
	
	
	@RequestMapping(value ="/{id}", method = RequestMethod.GET)	//Le paramètre id permet d'appeler le web service en ajoutant "id" à la fin de l'url
	//Le pathVariable me permet de dire que je vais affecter à l'id précisé dans l'url, un paramètre id de type Long
	public ProduitDTO getProduitById(@PathVariable("id") Long id) {
		//Ce parmaètre id, je vais le passer à la méthode GEt produit qui est dans produitService, puis retourner le produit puis je vai retourné le produit recherché dans l'url 
		return produitService.getProduit(id);
	}
	
/*@GetMapping
	public ResponseEntity<List <Produit>> getAllProduits(){
		System.out.println("getMapping method");
		try {
			return new ResponseEntity<>(produitService.getAllProduits(), HttpStatusCode.valueOf(200)); 
		}
		catch (Error error){
			System.out.println(error);
			return new ResponseEntity<>(produitService.getAllProduits(), HttpStatusCode.valueOf(404));
		}
	}*/
	
	/*Recupère un objet de type Produit gràce à a notation RequestBody. ça veut dire que dans le body de ma requête 
	 * http, je vais fournir un objet de de type Produit sous format Json. cet obejet 
	 * va être converti en objet de type produit, en suite je vais passer l'objet produit en paramètre de la méthode
	 * saveProduit qui est dévéloppée dans la couche service. Cette méthode va enregistrer le produit dans la base de donnée
	 * 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ProduitDTO createProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.saveProduit(produitDTO);
	}
	
	@PutMapping
	/*RequestBody pour récupérer l'objet produit, que je vais passer en parmètre à la méthode updateProduit
	 de la couche service, qui va mettre à jour notre produit dans la base de données*/
	public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO) {
		return produitService.updateProduit(produitDTO);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)/*On passe en paramètre l'id du produit à supprimer
	On recupère l'id à supprimer avec le PathVariavble, je le passe en paramètre de la methode deleteProduitById de la classe
	produitService*/
	public void deleteProduit(@PathVariable("id")Long id) {
		produitService.deleteProduitById(id);
	}
	//Retourne la catégorie du produit
	@RequestMapping(value ="/prodscat/{idCat}", method = RequestMethod.GET)
	public List<Produit> getProduitByCatId(@PathVariable("idCat") Long idCat){
		return produitService.findByCategorieIdCat(idCat);
	}
	
	/** Rechercher par nom de produits. Retourne une liste de produits, récupère le paramètre avec la méthode PathVariable
	 puis appelle la méthode findByNomProduitsContains*/
	@RequestMapping(value="/prodsByName/{nom}",method = RequestMethod.GET)
	public List<Produit> findByNomProduitContains(@PathVariable("nom") String nom) {
	return produitService.findByNomProduitContains(nom);
	}
	
	
}
