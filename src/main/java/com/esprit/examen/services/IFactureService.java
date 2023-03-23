package com.esprit.examen.services;

import java.util.Date;
import java.util.List;
import java.util.Set;
import com.esprit.examen.entities.DetailFacture;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entitiesdto.FactureDTO;

public interface IFactureService {
	List<Facture> retrieveAllFactures();

	List<Facture> getFacturesByFournisseur(Long idFournisseur) throws NullPointerException;

	Facture addFacture(Facture f);

	void cancelFacture(Long id);

	Facture retrieveFacture(Long id);

	Facture addDetailsFacture(Facture f, Set<DetailFacture> detailsFacture);

	void assignOperateurToFacture(Long idOperateur, Long idFacture) throws NullPointerException;

	float pourcentageRecouvrement(Date startDate, Date endDate);

	Facture mapping(FactureDTO fac );

}
