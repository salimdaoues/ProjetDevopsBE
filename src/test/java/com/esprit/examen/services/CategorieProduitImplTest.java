package com.esprit.examen.services;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.CategorieProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class CategorieProduitImplTest {
    @Autowired
    private CategorieProduitRepository categorieProduitRepository;

    @Autowired
    ICategorieProduitService categorieProduitService;
    @Test
    public void testRetrieveAllCategorieProduitsReturnsCorrectData() {
        CategorieProduit cat1 = new CategorieProduit( "makeup");
        CategorieProduit cat2 = new CategorieProduit("food");

        List<CategorieProduit> CategorieProduits = new ArrayList<>();
        CategorieProduits.add(cat1);
        CategorieProduits.add(cat2);
        categorieProduitService.addCategorieProduit(cat1);
        categorieProduitService.addCategorieProduit(cat2);
        List<CategorieProduit> actualcatProduits = categorieProduitService.retrieveAllCategorieProduits();

        // Assert
        assertEquals(CategorieProduits.size(), actualcatProduits.size());
        assertTrue(cat1.getIdCategorieProduit()==actualcatProduits.get(0).getIdCategorieProduit());
        assertTrue(cat2.getIdCategorieProduit()==actualcatProduits.get(1).getIdCategorieProduit());
        categorieProduitService.deleteCategorieProduit(cat1.getIdCategorieProduit());
        categorieProduitService.deleteCategorieProduit(cat2.getIdCategorieProduit());



    }
    @Test
    public void testUpdateCategorieProduit() {

        CategorieProduit catproduit = new CategorieProduit("test 1");
        CategorieProduit addedCat = categorieProduitService.addCategorieProduit(catproduit);
        assertNotNull(addedCat.getIdCategorieProduit());
        CategorieProduit retrievedCat = categorieProduitService.retrieveCategorieProduit(addedCat.getIdCategorieProduit());
        assertEquals("test 1", retrievedCat.getLibelleCategorie());
        retrievedCat.setLibelleCategorie("test 2");
        categorieProduitService.updateCategorieProduit(retrievedCat);
        CategorieProduit updatedCat = categorieProduitService.retrieveCategorieProduit(retrievedCat.getIdCategorieProduit());
        assertEquals("test 2", updatedCat.getLibelleCategorie());
        categorieProduitService.deleteCategorieProduit(updatedCat.getIdCategorieProduit());
    }

    @Test
    public void TestdeleteProduit(){
        CategorieProduit catproduit = new CategorieProduit("test 2");
        CategorieProduit savedcatproduit = categorieProduitService.addCategorieProduit(catproduit);
        categorieProduitService.deleteCategorieProduit(savedcatproduit.getIdCategorieProduit());
        assertNull(categorieProduitService.retrieveCategorieProduit(savedcatproduit.getIdCategorieProduit()));
    }
}
