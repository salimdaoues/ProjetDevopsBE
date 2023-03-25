package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entitiesdto.SecteurActiviteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;

@Service
public class SecteurActiviteServiceImpl implements ISecteurActiviteService{

	@Autowired
	SecteurActiviteRepository secteurActiviteRepository;
	@Override
	public List<SecteurActivite> retrieveAllSecteurActivite() {
		return (List<SecteurActivite>) secteurActiviteRepository.findAll();
	}

	@Override
	public SecteurActivite addSecteurActivite(SecteurActivite sa) {
		secteurActiviteRepository.save(sa);
		return sa;
	}

	@Override
	public void deleteSecteurActivite(Long id) {
		secteurActiviteRepository.deleteById(id);
		
	}

	@Override
	public SecteurActivite updateSecteurActivite(SecteurActivite sa2) {
		secteurActiviteRepository.save(sa2);
		return sa2;
	}

	@Override
	public SecteurActivite retrieveSecteurActivite(Long id) {
		return secteurActiviteRepository.findById(id).orElse(null);
	}

	@Override
	public SecteurActivite mapping(SecteurActiviteDTO sec) {
		SecteurActivite secteur = new SecteurActivite();
		secteur.setIdSecteurActivite(sec.getIdSecteurActivite());
		secteur.setCodeSecteurActivite(sec.getCodeSecteurActivite());
		secteur.setLibelleSecteurActivite(sec.getLibelleSecteurActivite());
		return secteur;
	}

}
