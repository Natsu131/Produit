package com.nat.produits;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.nat.produits.entities.Categorie;
import com.nat.produits.entities.Produit;


/*Par défaut Spring Data REST ne retourne pas la propriéte ID. Or on peut avoir
besoin de l’ID dans le résultat JSON si on utilise des frontend tels que Angular ou
ReactJS. Pour retourner l’ID, on doit faire la configuration suivante :*/

/*On modifie La classe ProduitsApplication, elle va implémenter l'inteface CommandLineRunner pour qu'elle nous ajoute
 une méthode Run qui va être éxécuter au démarage de l'application.
 On va faire une configuration dans cette méthode en utilisant un objet de type RepositoryRestConfiguration*/
@SpringBootApplication
public class ProduitsApplication implements CommandLineRunner {

	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;// Injection de dépendances pour utliser la variable
	
	public static void main(String[] args) {
		SpringApplication.run(ProduitsApplication.class, args);
	}
	
	
	@Override
	public void run(String... args) throws Exception {
	repositoryRestConfiguration.exposeIdsFor(Produit.class, Categorie.class); /*exposeIdFor, je demande à spring de me retourner l'id
	pour des objets de type Produit et de types Categorie*/
	}
	
	@Bean // Singleton de type ModelMapper
	public ModelMapper modelMapper(){
	
		return new ModelMapper();
	}

}
