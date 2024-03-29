package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entitiesdto.CategorieProduitDTO;


public interface ICategorieProduitService {

	List<CategorieProduit> retrieveAllCategorieProduits();

	CategorieProduit addCategorieProduit(CategorieProduit cp);

	void deleteCategorieProduit(Long id);

	CategorieProduit updateCategorieProduit(CategorieProduit cp);

	CategorieProduit retrieveCategorieProduit(Long id);

	CategorieProduit mapping(CategorieProduitDTO cat );

}
