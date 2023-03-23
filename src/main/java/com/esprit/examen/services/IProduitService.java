package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entitiesdto.ProduitDTO;

public interface IProduitService {

	List<Produit> retrieveAllProduits();

	Produit addProduit(Produit p);

	void deleteProduit(Long id);

	Produit updateProduit(Produit p);

	Produit retrieveProduit(Long id);

	void assignProduitToStock(Long idProduit, Long idStock);

	Produit mapping(ProduitDTO prod);

}
