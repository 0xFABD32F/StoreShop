package fstm.ilisi.Gestion_bibliotheque.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fstm.ilisi.Gestion_bibliotheque.entity.Produit;
import fstm.ilisi.Gestion_bibliotheque.repository.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProduitServiceImpl implements ProduitService {

    private final ProduitRepository produitRepository;

    @Override
    public Page<Produit> searchProduits(String search, int page, int size) {
        // Utiliser la méthode de recherche du repository
        return produitRepository.searchProduits(search, PageRequest.of(page, size));
    }

    @Override
    public Produit getProduitById(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produit non trouvé avec l'ID: " + id));
    }

    @Override
    public Produit saveProduit(Produit produit) {
        // Validation de base
        if (produit.getNom() == null || produit.getNom().trim().isEmpty()) {
            throw new RuntimeException("Le nom du produit ne peut pas être vide");
        }
        
        if (produit.getPrix() < 0) {
            throw new RuntimeException("Le prix ne peut pas être négatif");
        }
        
        if (produit.getStock() < 0) {
            throw new RuntimeException("Le stock ne peut pas être négatif");
        }
        
        return produitRepository.save(produit);
    }

    @Override
    public void deleteProduit(Long id) {
        if (!produitRepository.existsById(id)) {
            throw new RuntimeException("Produit non trouvé avec l'ID: " + id);
        }
        produitRepository.deleteById(id);
    }

    @Override
    public boolean produitExists(Long id) {
        return produitRepository.existsById(id);
    }
}
