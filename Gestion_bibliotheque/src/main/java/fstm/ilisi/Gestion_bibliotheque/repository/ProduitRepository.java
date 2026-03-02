package fstm.ilisi.Gestion_bibliotheque.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fstm.ilisi.Gestion_bibliotheque.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    
    // Recherche dans nom, description et nom de la catégorie
    @Query("SELECT p FROM Produit p WHERE " +
           "LOWER(p.nom) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(p.categorie.nom) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Produit> searchProduits(@Param("search") String search, Pageable pageable);
}
