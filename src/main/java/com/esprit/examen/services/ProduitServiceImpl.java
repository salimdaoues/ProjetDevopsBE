package com.esprit.examen.services;


import java.util.List;
import javax.transaction.Transactional;

import com.esprit.examen.entitiesdto.ProduitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.CategorieProduitRepository;
import com.esprit.examen.repositories.ProduitRepository;
import com.esprit.examen.repositories.StockRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProduitServiceImpl implements IProduitService {

	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	CategorieProduitRepository categorieProduitRepository;

	@Override
	public List<Produit> retrieveAllProduits() {
		List<Produit> produits =  produitRepository.findAll();
		for (Produit produit : produits) {
			log.info(" Produit : " + produit);
		}
		return produits;
	}

	@Transactional
	public Produit addProduit(Produit p) {
		produitRepository.save(p);
		return p;
	}

	

	@Override
	public void deleteProduit(Long produitId) {
		produitRepository.deleteById(produitId);
	}

	@Override
	public Produit updateProduit(Produit p) {
		return produitRepository.save(p);
	}

	@Override
	public Produit retrieveProduit(Long produitId) {
		Produit produit = produitRepository.findById(produitId).orElse(null);
		log.info("produit :" + produit);
		return produit;
	}

	@Override
	public void assignProduitToStock(Long idProduit, Long idStock) {
		Produit produit = produitRepository.findById(idProduit).orElse(null);
		Stock stock = stockRepository.findById(idStock).orElse(null);
		if(produit!=null){
			produit.setStock(stock);
			produitRepository.save(produit);
		}
		else{
			throw  new NullPointerException("produit null");
		}


	}

	@Override
	public Produit mapping(ProduitDTO prod) {
		Produit produit =new Produit("Produit 1", 10.99F);
		produit.setIdProduit(prod.getIdProduit());
		produit.setCodeProduit(prod.getCodeProduit());
		produit.setDateCreation(prod.getDateCreation());
		produit.setLibelleProduit(prod.getLibelleProduit());
		produit.setDateDerniereModification(prod.getDateDerniereModification());
		produit.setPrix(prod.getPrix());
		return produit;
	}


}