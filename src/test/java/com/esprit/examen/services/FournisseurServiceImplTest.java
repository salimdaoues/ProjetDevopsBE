package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Fournisseur;

import com.esprit.examen.repositories.FournisseurRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
 class FournisseurServiceImplTest {
    @Autowired
    private IFournisseurService fournisseurService;

    @Test
     void testRetrieveAllFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurService.retrieveAllFournisseurs();
        Assert.assertNotNull(fournisseurs);

    }

    @Test
     Fournisseur testAddFournisseur() {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur.setCode("FOURN01");
        fournisseur.setLibelle("Fournisseur 1");
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setAdresse("Rue du Commerce");
        detailFournisseur.setEmail("fournisseur1@fournisseur.com");
        detailFournisseur.setMatricule("MAT001");
        fournisseur.setDetailFournisseur(detailFournisseur);
        Fournisseur persistedFournisseur = fournisseurService.addFournisseur(fournisseur);
        Assert.assertNotNull(persistedFournisseur);
        Assert.assertNotNull(persistedFournisseur.getIdFournisseur());
        return persistedFournisseur;
    }

    @Test
     void testRetrieveFournisseur() {
        Fournisseur retrievedFournisseur = fournisseurService.retrieveFournisseur(testAddFournisseur().getIdFournisseur());
        Assert.assertNotNull(retrievedFournisseur);
    }

    @Test
     void testUpdateFournisseur() {
        Fournisseur persistedFournisseur = testAddFournisseur();
        persistedFournisseur.getDetailFournisseur().setAdresse("Nouvelle adresse");
        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(persistedFournisseur);
        Assert.assertEquals(persistedFournisseur.getDetailFournisseur().getAdresse(), updatedFournisseur.getDetailFournisseur().getAdresse());
    }

    @Test
     void testDeleteFournisseur() {
        Fournisseur persistedFournisseur = testAddFournisseur();
        fournisseurService.deleteFournisseur(persistedFournisseur.getIdFournisseur());
        Optional<Fournisseur> deletedFournisseur = Optional.ofNullable(fournisseurService.retrieveFournisseur(persistedFournisseur.getIdFournisseur()));
        Assert.assertFalse(deletedFournisseur.isPresent());
    }
}
