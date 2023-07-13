package com.nat.produits.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nat.produits.dto.ProduitDTO;
import com.nat.produits.entities.Categorie;
import com.nat.produits.entities.Produit;
import com.nat.produits.repos.ProduitRepository;

@Service
public class ProduitServiceImpl implements ProduitService {
	
	
	@Autowired
	ProduitRepository produitRepository;
	
	@Autowired
	ModelMapper modelMapper;
	

	@Override
	public ProduitDTO saveProduit(ProduitDTO p) {
		return convertEntityToDTO(produitRepository.save(convertDtoToEntity(p)));/*La méthode retourne une entité. On va le convertir en ProduitDTO
		grâce à la méthode crée convertEntityToDTO qui accepte comme paramètre le résultat, ce qui veut dire l'entité retournée*/
	}

	@Override
	public ProduitDTO updateProduit(ProduitDTO p) {
	
		return convertEntityToDTO(produitRepository.save(convertDtoToEntity(p)));
	}

	@Override
	public void deleteProduit(Produit P) {
		produitRepository.delete(P);
		
	}

	@Override
	public void deleteProduitById(Long id) {
		produitRepository.deleteById(id);
		
	}

	@Override
	public ProduitDTO getProduit(Long id) {
		return convertEntityToDTO(produitRepository.findById(id).get()); /*Ici c'est pareil, on retourne une entité
		qui est ensuite convertie en DTO*/
		
	}

	@Override
	public List<ProduitDTO> getAllProduits() {
		/*Méthode pour convertir en DTO - Programmation fonctionnelle - */
		
		return produitRepository.findAll().stream() /*Je veux appliquer la fonction convertEntityDTO à tous les éléments
				
				/*this :: (méthode référence) permet de référencer une méthode, ici je fais référence à ma méthode convertEntityTo DTO.
				cette fonction va être appliquer à tous les éléments de mon stream (de ma liste) c'est à dire tous les éléments de la liste qui sont retournée par findAll*/
				.map(this:: convertEntityToDTO)
				.collect(Collectors.toList()); // Je retourne au type list. 
		//OU BIEN
		
		/*List<Produit> prods = produitRepository.findAll();// Je déclare une liste de produits qui s'appelle prods, dans laquelle je récupère le résultat de findAll qui nous retourne une list de produits d'entité Produit
		List<ProduitDTO> listprodDto = new ArrayList<>(prods.size()); // Je déclare ensuite une autre liste qui s'appelle listprodDTO qui est ue liste de DTO dans laquelle je réserve un élement d type list DTO. Ce sera la taille de ma liste prods qui contient tous les éléments, ou produit retourner par le findAll
		for (Produit p : prods)//Je parcours les éléments de l liste prods, qu'on appelle p ici. Pour chaque P, je vais lancer la méthode convertEntityDTO, qui va convertir le produit p en produitDTO et j'ajoue le résultat à liste, qui est listprodDTO (qui est vide).
		listprodDto.add(convertEntityToDto(p));
		return listprodDto;*/	//En quittant cette boucle, j'aurais la listeDTO remplie par des objets de type DTO et je la retourne	
				
	}

	@Override
	public List<Produit> findByNomProduit(String nom) {
		
		return produitRepository.findByNomProduit(nom);
	}

	@Override
	public List<Produit> findByNomProduitContains(String nom) {
		
		return produitRepository.findByNomProduitContains(nom);
	}

	@Override
	public List<Produit> findByNomPrix(String nom, Double prix) {
		
		return produitRepository.findByNomPrix(nom, prix);
	}

	@Override
	public List<Produit> findByCategorie(Categorie categorie) {
	
		return produitRepository.findByCategorie(categorie);
	}

	@Override
	public List<Produit> findByCategorieIdCat(Long id) {
	
		return produitRepository.findByCategorieIdCat(id);
	}

	@Override
	public List<Produit> findByOrderByNomProduitAsc() {
		
		return produitRepository.findByOrderByNomProduitAsc();
	}

	@Override
	public List<Produit> trierProduitsNomsPrix() {
		
		return produitRepository.trierProduitsNomsPrix();
	}
	
	/*Reçois un argument une entité Produit pour la transformer en produit DTO et le retourner*/
	@Override
	public ProduitDTO convertEntityToDTO(Produit p) {
		
	/*	ProduitDTO produitDTO = new ProduitDTO(); //Je vais remplir cette objet à partir de l'objet avec les getteurs et setteurs
			produitDTO.setIdProduit(p.getIdProduit()); // Je récupère l'id produit
			produitDTO.setNomProduit(p.getNomProduit());
			produitDTO.setPrixProduit(p.getPrixProduit());
			prduitDTO.setDateCreation(p.getDateCretion());
			produitDTO.setCategorie(p.getCategorie());
		return produitDTO;
	}*/
	
	/*Deuxième façon de faire. On l'utilise plus que l'opérateur new*/
	return ProduitDTO.builder()
				.idProduit(p.getIdProduit())
				.nomProduit(p.getNomProduit())
				.prixProduit(p.getPrixProduit())
				.dateCreation(p.getDateCreation())
				.categorie(p.getCategorie())
				.build();
	}

	//Reçoit comme parmètre un produitDTO, crée un produit, le rempli et le retourne
	//On fait ceci ici pour ne pas que les entités soient manipulées au niveau de ma coucher RestController
	@Override
	public Produit convertDtoToEntity(ProduitDTO produitDTO) {
		Produit produit = new Produit();
		produit.setIdProduit(produitDTO.getIdProduit());
		produit.setNomProduit(produitDTO.getNomProduit());
		produit.setPrixProduit(produitDTO.getPrixProduit());
		produit.setDateCreation(produitDTO.getDateCreation());
		produit.setCategorie(produitDTO.getCategorie());
		return produit;
	}
}
