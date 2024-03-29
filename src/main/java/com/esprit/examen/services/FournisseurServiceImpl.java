package com.esprit.examen.services;

import java.util.*;


import com.esprit.examen.entities.*;
import com.esprit.examen.entitiesdto.FournisseurDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.repositories.DetailFournisseurRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FournisseurServiceImpl implements IFournisseurService {

	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	DetailFournisseurRepository detailFournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;

	@Override
	public List<Fournisseur> retrieveAllFournisseurs() {
		List<Fournisseur> fournisseurs = fournisseurRepository.findAll();
		for (Fournisseur fournisseur : fournisseurs) {
			log.info(" fournisseur : " + fournisseur);
		}
		return fournisseurs;
	}


	public Fournisseur addFournisseur(Fournisseur f /*Master*/) {
		DetailFournisseur df= new DetailFournisseur();//Slave
		df.setDateDebutCollaboration(new Date()); //util
		//On affecte le "Slave" au "Master"
		f.setDetailFournisseur(df);	
		fournisseurRepository.save(f);
		return f;
	}
	
	private DetailFournisseur  saveDetailFournisseur(Fournisseur f){
		DetailFournisseur df = f.getDetailFournisseur();
		detailFournisseurRepository.save(df);
		return df;
	}

	public Fournisseur updateFournisseur(Fournisseur f) {
		DetailFournisseur df = saveDetailFournisseur(f);
		f.setDetailFournisseur(df);	
		fournisseurRepository.save(f);
		return f;
	}

	@Override
	public void deleteFournisseur(Long fournisseurId) {
		fournisseurRepository.deleteById(fournisseurId);

	}

	@Override
	public Fournisseur retrieveFournisseur(Long fournisseurId) {
		return fournisseurRepository.findById(fournisseurId).orElse(null);
	}

	@Override
	public void assignSecteurActiviteToFournisseur(Long idSecteurActivite, Long idFournisseur) {
		Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
		SecteurActivite secteurActivite = secteurActiviteRepository.findById(idSecteurActivite).orElse(null);
        if(fournisseur!= null){
			fournisseur.getSecteurActivites().add(secteurActivite);
			fournisseurRepository.save(fournisseur);
		}
        else{
        	throw new NullPointerException("fournisseur null");
		}
	}

	@Override
	public Fournisseur mapping(FournisseurDTO f) {
		Fournisseur persistentfournisseur = new Fournisseur();
		persistentfournisseur.setCategorieFournisseur(f.getCategorieFournisseur());
		persistentfournisseur.setIdFournisseur(f.getIdFournisseur());
		persistentfournisseur.setCode(f.getCode());
		persistentfournisseur.setLibelle(f.getLibelle());
		persistentfournisseur.setDetailFournisseur(f.getDetailFournisseur());
		return persistentfournisseur;
	}


}