package com.esprit.examen.services;

import java.util.Date;
import java.util.List;

import com.esprit.examen.entitiesdto.ReglementDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;

@Service
public class ReglementServiceImpl implements IReglementService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	ReglementRepository reglementRepository;
	@Override
	public List<Reglement> retrieveAllReglements() {
		return (List<Reglement>) reglementRepository.findAll();
	}

	@Override
	public Reglement addReglement(Reglement r) {
        reglementRepository.save(r);
		return r;
	}

	@Override
	public Reglement retrieveReglement(Long id) {
		return reglementRepository.findById(id).orElse(null);
	}

	@Override
	public List<Reglement> retrieveReglementByFacture(Long idFacture) {
		return reglementRepository.retrieveReglementByFacture(idFacture);
		
//		ou bien(Sans JPQL)
	}

	@Override
	public float getChiffreAffaireEntreDeuxDate(Date startDate, Date endDate) {
		return reglementRepository.getChiffreAffaireEntreDeuxDate( startDate, endDate);
	}

	@Override
	public Reglement mapping(ReglementDTO reg) {
		Reglement reglement = new Reglement();
		reglement.setIdReglement(reg.getIdReglement());
		reglement.setDateReglement(reg.getDateReglement());
		reglement.setMontantPaye(reg.getMontantPaye());
		reglement.setPayee(reg.getPayee());
		reglement.setMontantRestant(reg.getMontantRestant());
		return reglement;
	}

}
