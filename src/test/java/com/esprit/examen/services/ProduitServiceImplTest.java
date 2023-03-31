package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
  @MockitoSettings(strictness = Strictness.LENIENT)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
 class ProduitServiceImplTest {
    @Autowired
    private IProduitService produitService;

    @Test
     void TestretrieveAllProduits(){
        List<Produit> produits= produitService.retrieveAllProduits();
        Assert.assertNotNull(produits);
    }
    @Test
     void testRetrieveAllProduitsReturnsCorrectData() {
        int expected = produitService.retrieveAllProduits().size();

        Produit expectedProduit1 = new Produit("Produit 1", 10.99F);
        Produit expectedProduit2 = new Produit("Produit 2", 20.99F);
        //List<Produit> expectedProduits = new ArrayList<>();
        produitService.addProduit(expectedProduit1);
        produitService.addProduit(expectedProduit2);
        List<Produit> actualProduits = produitService.retrieveAllProduits();

        // Assert
        assertEquals(expected+2, actualProduits.size());
        assertEquals(expectedProduit1.getLibelleProduit(), actualProduits.get(0).getLibelleProduit());
        Assertions.assertEquals(expectedProduit1.getPrix(),actualProduits.get(0).getPrix());
        assertEquals(expectedProduit2.getLibelleProduit(), actualProduits.get(1).getLibelleProduit());
        Assertions.assertEquals(expectedProduit2.getPrix(), actualProduits.get(1).getPrix());

    }




   @Test
    void TestaddProduit(){
        Produit produit = new Produit("test 1",12F);
        Produit savedproduit = produitService.addProduit(produit) ;
        assertSame("test 1",savedproduit.getLibelleProduit());
        Assertions.assertEquals(produit.getPrix(),savedproduit.getPrix());
        assertNotNull(savedproduit.getIdProduit());
        produitService.deleteProduit(savedproduit.getIdProduit());
    }
   @Test
    void TestdeleteProduit(){
       Produit produit = new Produit("test 2",13F);
       Produit savedproduit = produitService.addProduit(produit) ;
       produitService.deleteProduit(savedproduit.getIdProduit());
       assertNull(produitService.retrieveProduit(savedproduit.getIdProduit()));
   }

    @Test
     void testDeleteNonexistentProduit() {
        Produit produit = new Produit("test 3",14F);
        Produit savedproduit = produitService.addProduit(produit) ;
        long produitId = savedproduit.getIdProduit();
        produitService.deleteProduit(produitId);
        assertNull(produitService.retrieveProduit(produitId));
    }
@Test
      void TestupdateProduit(){
        Produit produit = new Produit("test 4",15F);
        Produit p1= produitService.addProduit(produit);
        assertNotNull(p1.getIdProduit());
        Produit p2= produitService.retrieveProduit(p1.getIdProduit());
        assertTrue(p2.getPrix()>0);
        produitService.deleteProduit(p1.getIdProduit());
    }

























}
