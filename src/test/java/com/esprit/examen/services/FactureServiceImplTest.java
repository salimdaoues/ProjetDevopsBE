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
    Facture facture1 = new Facture();
        facture1.setArchivee(true);
        facture1.setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDateDerniereModificationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDetailsFacture(new HashSet<>());
        facture1.setIdFacture(1L);
        facture1.setMontantFacture(10.0f);
        facture1.setMontantRemise(10.0f);
        facture1.setReglements(new HashSet<>());
    assertSame(facture1, factService.addFacture(facture1));
    verify(factRepository).save((Facture) any());
        factService.retrieveFacture(factService.addFacture(facture1).getIdFacture());
    }
    @Test
    void testUpdateFacture() {

        Facture facture1 = new Facture();
        facture1.setArchivee(true);
        facture1.setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDateDerniereModificationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDetailsFacture(new HashSet<>());
        facture1.setIdFacture(1L);
        facture1.setMontantFacture(10.0f);
        facture1.setMontantRemise(10.0f);
        facture1.setReglements(new HashSet<>());
        assertSame(facture1, factService.addFacture(facture1));
        verify(factRepository).save((Facture) any());
        factService.retrieveFacture(factService.addFacture(facture1).getIdFacture());

    }
    @Test
    void testRetrieveFacture() {
        Facture facture1 = new Facture();
        facture1.setArchivee(true);
        facture1.setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDateDerniereModificationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDetailsFacture(new HashSet<>());
        facture1.setIdFacture(1L);
        facture1.setMontantFacture(10.0f);
        facture1.setMontantRemise(10.0f);
        facture1.setReglements(new HashSet<>());
        Optional<Facture> ofResult = Optional.of(facture1);
        when(factRepository.findById((Long) any())).thenReturn(ofResult);
        Assert.assertSame(facture1, factService.retrieveFacture(1L));
        verify(factRepository).findById((Long) any());
    }
    @Test
    void testDeleteStock() {
        Facture facture1 = new Facture();
        facture1.setArchivee(true);
        facture1.setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDateDerniereModificationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDetailsFacture(new HashSet<>());
        facture1.setIdFacture(1L);
        facture1.setMontantFacture(10.0f);
        facture1.setMontantRemise(10.0f);
        facture1.setReglements(new HashSet<>());
        assertSame(facture1, factService.addFacture(facture1));
        factService.retrieveFacture(factService.addFacture(facture1).getIdFacture());
        assertNull(factService.retrieveFacture(factService.addFacture(facture1).getIdFacture()));
    }
}

