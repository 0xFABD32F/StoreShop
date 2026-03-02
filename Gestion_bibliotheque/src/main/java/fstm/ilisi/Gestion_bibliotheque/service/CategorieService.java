package fstm.ilisi.Gestion_bibliotheque.service;

import java.util.List;

import fstm.ilisi.Gestion_bibliotheque.entity.Categorie;

public interface CategorieService {

    List<Categorie> getAllCategories();
    
    Categorie getCategorieById(Long id);
    
    Categorie saveCategorie(Categorie categorie);
    
    void deleteCategorie(Long id);
    
    boolean categorieExists(String nom);
}
