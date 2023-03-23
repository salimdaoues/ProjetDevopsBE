package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.entitiesdto.SecteurActiviteDTO;

public interface ISecteurActiviteService {

	List<SecteurActivite> retrieveAllSecteurActivite();

	SecteurActivite addSecteurActivite(SecteurActivite sa);

	void deleteSecteurActivite(Long id);

	SecteurActivite updateSecteurActivite(SecteurActivite sa);

	SecteurActivite retrieveSecteurActivite(Long id);

	SecteurActivite mapping(SecteurActiviteDTO sec);

}
