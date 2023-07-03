package com.nat.produits;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nat.produits.entities.Categorie;
import com.nat.produits.entities.Produit;
import com.nat.produits.repos.ProduitRepository;

@SpringBootTest
class ProduitsApplicationTests {

	@Autowired
	private ProduitRepository produitRepository;
	@Test
	public void testCreateProduit() {
		Produit prods = new Produit("PS5", 700.99, new Date());
		produitRepository.save(prods);
	}
	
	@Test
	public void testFindProduit() {
		Produit p = produitRepository.findById(1L).get();
		System.out.println(p);
	}
	@Test
	public void testUpdateProduit() {
		Produit p = produitRepository.findById(1L).get();
		p.setPrixProduit(2000.0);
		produitRepository.save(p);
		
		System.out.println(p);
	}
	@Test
	public void testDeleteProduit() {
		produitRepository.deleteById(3L);
	}
	
	@Test
	public void testFindAllProduit() {
		List<Produit> prods = produitRepository.findAll();
		for(Produit p:prods) {
			System.out.println(p);
		}
	}
	@Test
	public void testFindProduitByNom() {
		List<Produit> prods = produitRepository.findByNomProduit("PC MSI");
		
		for (Produit p:prods) {
			System.out.println(p);
		}
	}
	
	@Test
	public void testfindProduitByNomContains() {
		List<Produit> prods = produitRepository.findByNomProduitContains("M");
		
		for (Produit p:prods) {
			System.out.println(p);
		}
	}
	
	@Test
	public void testfindByNomPrix() {
		List<Produit> prods = produitRepository.findByNomPrix("PC MSI", 1000.0);
		for (Produit p:prods) {
			System.out.println(p);
		}
	}
	@Test
	public void testfindByCategorie() {
		Categorie cat = new Categorie();
		cat.setIdCat(1L); //J'affecte 1 à la valeur d'ID de mon objet cat, qui vaut maintenant id 1)
		List<Produit> prods = produitRepository.findByCategorie(cat);
		for (Produit p : prods) {
			System.out.println(p);
		}
	}
		//Chercher la différence entre la méthode précédente et celle d'après
	@Test
	public void testfindByCategorieIdCat() {
		List<Produit> prods = produitRepository.findByCategorieIdCat(1L);
		for (Produit p : prods) {
			System.out.println(p);
		}
	}
	
	@Test
	public void testfindByOrderByNomProduitAsc(){
	List<Produit> prods =
	produitRepository.findByOrderByNomProduitAsc();
	for (Produit p : prods){
	System.out.println(p);
		}
	}
	
	@Test
	public void testTrierProduitsNomsPrix()
	{
	List<Produit> prods = produitRepository.trierProduitsNomsPrix();
	for (Produit p : prods)
	{
	System.out.println(p);
	}
	}
	
}
