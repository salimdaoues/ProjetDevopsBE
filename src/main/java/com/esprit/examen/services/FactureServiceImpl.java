package com.esprit.examen.services;

import java.util.*;
import javax.transaction.Transactional;

import com.esprit.examen.entities.*;
import com.esprit.examen.entitiesdto.FactureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.repositories.DetailFactureRepository;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.OperateurRepository;
import com.esprit.examen.repositories.ProduitRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class FactureServiceImpl implements IFactureService {

	@Autowired
	FactureRepository factureRepository;
	@Autowired
	OperateurRepository operateurRepository;
	@Autowired
	DetailFactureRepository detailFactureRepository;
	@Autowired
	FournisseurRepository fournisseurRepository;
	@Autowired
	ProduitRepository produitRepository;
    @Autowired
    ReglementServiceImpl reglementService;
	
	@Override
	public List<Facture> retrieveAllFactures() {
		List<Facture> factures =  factureRepository.findAll();
		for (Facture facture : factures) {
			log.info(" facture : " + facture);
		}
		return factures;
	}

	
	public Facture addFacture(Facture f) {
		return factureRepository.save(f);
	}

	/*
	 * calculer les montants remise et le montant total d'un détail facture
	 * ainsi que les montants d'une facture
	 */
	public Facture addDetailsFacture(Facture f, Set<DetailFacture> detailsFacture) {
		float montantFacture = 0;
		float montantRemise = 0;
		for (DetailFacture detail : detailsFacture) {
			Optional<Produit> pr =produitRepository.findById(detail.getProduit().getIdProduit());
			if(pr.isPresent()){
			//Récuperer le produit 
			Produit produit = pr.get();
			//Calculer le montant total pour chaque détail Facture
			float prixTotalDetail = detail.getQteCommandee() * produit.getPrix();
			//Calculer le montant remise pour chaque détail Facture
			float montantRemiseDetail = (prixTotalDetail * detail.getPourcentageRemise()) / 100;
			float prixTotalDetailRemise = prixTotalDetail - montantRemiseDetail;
			detail.setMontantRemise(montantRemiseDetail);
			detail.setPrixTotalDetail(prixTotalDetailRemise);
			//Calculer le montant total pour la facture
			montantFacture = montantFacture + prixTotalDetailRemise;
			//Calculer le montant remise pour la facture
			montantRemise = montantRemise + montantRemiseDetail;
			detailFactureRepository.save(detail);
			}
		}
		f.setMontantFacture(montantFacture);
		f.setMontantRemise(montantRemise);
		return f;
	}

	@Override
	public void cancelFacture(Long factureId) {
		// Méthode 01
		Facture facture = factureRepository.findById(factureId).orElse(new Facture());
		facture.setArchivee(true);
		factureRepository.save(facture);
		//Méthode 02 (Avec JPQL)
		factureRepository.updateFacture(factureId);
	}

	@Override
	public Facture retrieveFacture(Long factureId) {

		Facture facture = factureRepository.findById(factureId).orElse(null);
		log.info("facture :" + facture);
		return facture;
	}

	@Override
	public List<Facture> getFacturesByFournisseur(Long idFournisseur) throws NullPointerException {
		Fournisseur fournisseur = fournisseurRepository.findById(idFournisseur).orElse(null);
		if(fournisseur !=null){
			return (List<Facture>) fournisseur.getFactures();
		}
		else{
			throw new NullPointerException("fournisseur NULL");
		}
}

	@Override
	public void assignOperateurToFacture(Long idOperateur, Long idFacture) throws NullPointerException {
		Facture facture = factureRepository.findById(idFacture).orElse(null);
		Operateur operateur = operateurRepository.findById(idOperateur).orElse(null);
		if(operateur!=null)
		{operateur.getFactures().add(facture);
			operateurRepository.save(operateur);
		}
		else {
			throw new NullPointerException("operateur null");
		}
	}

	@Override
	public float pourcentageRecouvrement(Date startDate, Date endDate) {
		float totalFacturesEntreDeuxDates = factureRepository.getTotalFacturesEntreDeuxDates(startDate,endDate);
		float totalRecouvrementEntreDeuxDates =reglementService.getChiffreAffaireEntreDeuxDate(startDate,endDate);
		return 	(totalRecouvrementEntreDeuxDates/totalFacturesEntreDeuxDates)*100;

	}

	@Override
	public Facture mapping(FactureDTO fac) {
		Facture persistentfacture = new Facture();
		persistentfacture.setArchivee(fac.getArchivee());
		persistentfacture.setMontantFacture(fac.getMontantFacture());
		persistentfacture.setDateCreationFacture(fac.getDateCreationFacture());
		persistentfacture.setIdFacture(fac.getIdFacture());
		persistentfacture.setMontantFacture(fac.getMontantFacture());
		persistentfacture.setMontantRemise(fac.getMontantRemise());
		persistentfacture.setDetailsFacture(fac.getDetailsFacture());
		return persistentfacture;
	}


}