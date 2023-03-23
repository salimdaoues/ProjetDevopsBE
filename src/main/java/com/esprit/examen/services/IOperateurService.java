package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.entitiesdto.OperateurDTO;


public interface IOperateurService {

	List<Operateur> retrieveAllOperateurs();

	Operateur addOperateur(Operateur o);

	void deleteOperateur(Long id);

	Operateur updateOperateur(Operateur o);

	Operateur retrieveOperateur(Long id);

	Operateur mapping(OperateurDTO op);
}
