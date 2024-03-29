package com.esprit.examen.services;
import java.util.List;
import com.esprit.examen.entitiesdto.CategorieProduitDTO;
import com.esprit.examen.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

@Service
public class CategorieProduitServiceImpl implements ICategorieProduitService {

	
	@Autowired
	CategorieProduitRepository categorieProduitRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Override
	public List<CategorieProduit> retrieveAllCategorieProduits() {
		
		return categorieProduitRepository.findAll();
	}

	@Override
	public CategorieProduit addCategorieProduit(CategorieProduit cp) {
		categorieProduitRepository.save(cp);
		return cp;
	}

	@Override
	public void deleteCategorieProduit(Long id) {
		categorieProduitRepository.deleteById(id);
		
	}

	@Override
	public CategorieProduit updateCategorieProduit(CategorieProduit cp2) {
		categorieProduitRepository.save(cp2);
		return cp2;
	}

	@Override
	public CategorieProduit retrieveCategorieProduit(Long id) {
		return  categorieProduitRepository.findById(id).orElse(null);
	}

	@Override
	public CategorieProduit mapping(CategorieProduitDTO cat) {
		CategorieProduit persistentcatproduit = new CategorieProduit();
		persistentcatproduit.setIdCategorieProduit(cat.getIdCategorieProduit());
		persistentcatproduit.setLibelleCategorie(cat.getLibelleCategorie());
		persistentcatproduit.setCodeCategorie(cat.getCodeCategorie());
		return persistentcatproduit;
	}

}
