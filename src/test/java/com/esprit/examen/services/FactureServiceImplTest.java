package com.esprit.examen.services;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
 class FactureServiceImplTest {
    @Autowired
    IFactureService factService;
    @Autowired
    FactureRepository factRepository;
    @Test
    void testAddFacture() {
        Facture f = new Facture(5,98,true);
        Facture savedfact= factService.addFacture(f);
        float expectedMontantFacture = 98f;
        float delta = 0.001f;
        assertEquals(expectedMontantFacture, savedfact.getMontantFacture(), delta);
        assertNotNull(savedfact.getIdFacture());
    }
    @Test
    void testUpdateFacture() {

        Facture f = new Facture(9,30,true);
        Facture f2= factService.addFacture(f);
        Float montantFacture = f2.getMontantFacture();
        assertNotNull(montantFacture);
        Facture fact= factService.retrieveFacture(f2.getIdFacture());
        assertEquals(30f, fact.getMontantFacture(), 0.001f);
    }
    @Test
    void testretrieveallFactures() {
        int expected = factService.retrieveAllFactures().size();
        Facture f = new Facture(1000,68,false);
        Facture savedFact= factService.addFacture(f);
        assertEquals(expected + 1, factService.retrieveAllFactures().size());
    }

}

