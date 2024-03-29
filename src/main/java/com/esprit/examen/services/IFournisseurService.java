package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entitiesdto.FournisseurDTO;

public interface IFournisseurService {

	List<Fournisseur> retrieveAllFournisseurs();

	Fournisseur addFournisseur(Fournisseur f);

	void deleteFournisseur(Long id);

	Fournisseur updateFournisseur(Fournisseur f);

	Fournisseur retrieveFournisseur(Long id);
	
	void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur);

	Fournisseur mapping(FournisseurDTO f);

}
