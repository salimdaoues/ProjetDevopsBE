package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Stock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class ProduitServiceImplTest {
    @Autowired
    private IProduitService produitService;

    @Test
    public void TestretrieveAllProduits(){
        List<Produit> produits= produitService.retrieveAllProduits();
        Assert.assertNotNull(produits);
    }
    @Test
    public void testRetrieveAllProduitsReturnsCorrectData() {
        Produit expectedProduit1 = new Produit("Produit 1", 10.99F);
        Produit expectedProduit2 = new Produit("Produit 2", 20.99F);

        List<Produit> expectedProduits = new ArrayList<>();
        expectedProduits.add(expectedProduit1);
        expectedProduits.add(expectedProduit2);
        when(produitService.retrieveAllProduits()).thenReturn(expectedProduits);
        List<Produit> actualProduits = produitService.retrieveAllProduits();

        // Assert
        assertEquals(expectedProduits.size(), actualProduits.size());
        assertEquals(expectedProduit1.getLibelleProduit(), actualProduits.get(0).getLibelleProduit());
        assertEquals(expectedProduit1.getPrix(), actualProduits.get(0).getPrix());
        assertEquals(expectedProduit2.getLibelleProduit(), actualProduits.get(1).getLibelleProduit());
        assertEquals(expectedProduit2.getPrix(), actualProduits.get(1).getPrix());

    }




   @Test
   public void TestaddProduit(){
        Produit produit = new Produit("test 1",12F);
        Produit savedproduit = produitService.addProduit(produit) ;
        assertSame("test 1",savedproduit.getLibelleProduit());
        assertSame(12F,savedproduit.getPrix());
        produitService.deleteProduit(savedproduit.getIdProduit());
    }
   @Test
   public void TestdeleteProduit(){
       Produit produit = new Produit("test 2",13F);
       Produit savedproduit = produitService.addProduit(produit) ;
       produitService.deleteProduit(savedproduit.getIdProduit());
       assertNull(produitService.retrieveProduit(savedproduit.getIdProduit()));
   }

    @Test
    public void testDeleteNonexistentProduit() {
        Produit produit = new Produit("test 3",14F);
        Produit savedproduit = produitService.addProduit(produit) ;
        long produitId = savedproduit.getIdProduit();

        //first time
        produitService.deleteProduit(produitId);
        //second time
        produitService.deleteProduit(produitId);


        assertNull(produitService.retrieveProduit(produitId));
    }
@Test
     public void TestupdateProduit(){
        Produit produit = new Produit("test 4",15F);
        Produit p1= produitService.addProduit(produit);
        assertNotNull(p1.getIdProduit());
        Produit p2= produitService.retrieveProduit(p1.getIdProduit());
        assertTrue(p2.getPrix()>0);
        produitService.deleteProduit(p1.getIdProduit());
    }

























}
