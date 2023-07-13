package com.nat.produits.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nat.produits.entities.Categorie;

@RepositoryRestResource(path = "cat")//Pour dire à spring que je veux toutes les api nécessaires pour faire ls manipulations des catégories
@CrossOrigin("http://localhost:5200/") // Autoriastion à angular d'accéder aux api
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}
