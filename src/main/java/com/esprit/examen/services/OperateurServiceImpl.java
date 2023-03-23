package com.esprit.examen.services;

import java.util.List;

import com.esprit.examen.entitiesdto.OperateurDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

@Service
public class OperateurServiceImpl implements IOperateurService {

	@Autowired
	OperateurRepository  operateurRepository;
	@Override
	public List<Operateur> retrieveAllOperateurs() {
		return (List<Operateur>) operateurRepository.findAll();
	}

	@Override
	public Operateur addOperateur(Operateur o) {
		operateurRepository.save(o);
		return o;
	}

	@Override
	public void deleteOperateur(Long id) {
		operateurRepository.deleteById(id);
		
	}

	@Override
	public Operateur updateOperateur(Operateur o2) {
		operateurRepository.save(o2);
		return o2;
	}

	@Override
	public Operateur retrieveOperateur(Long id) {
		return operateurRepository.findById(id).orElse(null);
	}

	@Override
	public Operateur mapping(OperateurDTO op) {
		Operateur operateur = new Operateur();
		operateur.setIdOperateur(op.getIdOperateur());
		operateur.setNom(op.getNom());
		operateur.setPrenom(op.getPrenom());
	    operateur.setPassword(op.getPassword());
	    return operateur;
	}

}
