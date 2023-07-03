package com.nat.produits.entities;

import org.springframework.data.rest.core.config.Projection;

/*Restreindre les données avec les Projections
L’objectif des projections est de limiter le résultat JSON retourné à un certain nombre
d’attributs. Par exemple on peut avoir besoin seulement de l’attribut nomProduit */

@Projection(name = "nomProd", types = { Produit.class })//La projection concerne l'entité Produit
public interface ProduitProjection {
	public String getNomProduit();//Je mets le get des attributs que je vais consulter
	/*Pour tester, je mets l'adresse rest et comme paramètre je mets la projection + le nom de la projetction 
	 * que j'ai appelé nomProd : http://localhost:8080/produits/rest?projection=nomProd*/
}
