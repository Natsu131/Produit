package com.nat.produits.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.nat.produits.entities.Categorie;
import com.nat.produits.entities.Produit;

@RepositoryRestResource(path = "rest")/*Le rest est optionel. Si je ne le mets pas, les web services seront disponible 
à la racine de mon projet, c'est à dire localhost:8080/produits (comme avec le préfixe "api"
Je peux demander à spring de générer les web services en tapant /rest à la fin
Exempke de test : http://localhost:3000/produits/rest?sort=nomProduit,desc ce lient permet de consulter tous les produits en les créant sur l'attribut nom produit
et dans l'ordre croissant. Le premier paramètre c'est l'attribut sur lequel je veux faire le tri
et le deuxième le sens de tri  - http://localhost:3000/produits/rest?sort=prixProduit,asc, ici je tri le prix par ordre
croissant*/
public interface ProduitRepository extends JpaRepository<Produit, Long> {
	//Il peut y avoir plusieurs produits qui ont mêmme nom, donc cela nous retourne une list de produit
	List<Produit> findByNomProduit(String nom);
	List<Produit> findByNomProduitContains(String nom);
	
	//Ici on a des paramètres positionnels (1,2...)
	/*@Query("select p from Produit p where p.nomProduit like %?1 and p.prixProduit > ?2")
	
	List<Produit> findByNomPrix (String nom, Double prix);*/
	
	//Paramètre nommés
	@Query("select p from Produit p where p.nomProduit like %:nom and p.prixProduit > :prix")
	List<Produit> findByNomPrix (@Param("nom") String nom, @Param("prix") Double prix);
	
	@Query("select p from Produit p where p.categorie = ?1")
	List<Produit> findByCategorie(Categorie categorie);
	
	List<Produit> findByCategorieIdCat(Long id);
	
	List<Produit> findByOrderByNomProduitAsc();// je trie les produits dans l'ordre croissant (asc) 
	
	@Query("select p from Produit p order by p.nomProduit ASC, p.prixProduit DESC")
	List<Produit> trierProduitsNomsPrix ();
}