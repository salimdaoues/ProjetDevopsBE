package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ReglementServiceImpl.class})
@ExtendWith(MockitoExtension.class)
class ReglementServiceImplTest {
    @Mock
    private FactureRepository factureRepository;

    @Mock
    private ReglementRepository reglementRepository;

    @InjectMocks
    private ReglementServiceImpl reglementServiceImpl;


    /**
     * Method under test: {@link ReglementServiceImpl#addReglement(Reglement)}
     */
    @Test
    void testAddReglement() {

        Reglement reglement = new Reglement();
        reglement.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(10.0f);
        reglement.setMontantRestant(10.0f);
        reglement.setPayee(true);
        when(reglementRepository.save((Reglement) any())).thenReturn(reglement);

        DetailFournisseur detailFournisseur1 = new DetailFournisseur();
        detailFournisseur1.setAdresse("Adresse");
        detailFournisseur1.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur1.setEmail("jane.doe@example.org");
        detailFournisseur1.setFournisseur(new Fournisseur());
        detailFournisseur1.setIdDetailFournisseur(1L);
        detailFournisseur1.setMatricule("Matricule");

        Fournisseur fournisseur2 = new Fournisseur();
        fournisseur2.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur2.setCode("Code");
        fournisseur2.setDetailFournisseur(detailFournisseur1);
        fournisseur2.setFactures(new HashSet<>());
        fournisseur2.setIdFournisseur(1L);
        fournisseur2.setLibelle("Libelle");
        fournisseur2.setSecteurActivites(new HashSet<>());

        Facture facture1 = new Facture();
        facture1.setArchivee(true);
        facture1
                .setDateCreationFacture(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture1.setDetailsFacture(new HashSet<>());
        facture1.setFournisseur(fournisseur2);
        facture1.setIdFacture(1L);
        facture1.setMontantFacture(10.0f);
        facture1.setMontantRemise(10.0f);
        facture1.setReglements(new HashSet<>());

        Reglement reglement1 = new Reglement();
        reglement1.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement1.setFacture(facture1);
        reglement1.setIdReglement(1L);
        reglement1.setMontantPaye(10.0f);
        reglement1.setMontantRestant(10.0f);
        reglement1.setPayee(true);
        assertSame(reglement1, reglementServiceImpl.addReglement(reglement1));
        verify(reglementRepository).save((Reglement) any());
    }

    /**
     * Method under test: {@link ReglementServiceImpl#retrieveReglement(Long)}
     */
    @Test
    void testRetrieveReglement() {
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setAdresse("Adresse");
        detailFournisseur.setDateDebutCollaboration(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        detailFournisseur.setEmail("jane.doe@example.org");
        detailFournisseur.setFournisseur(new Fournisseur());
        detailFournisseur.setIdDetailFournisseur(1L);
        detailFournisseur.setMatricule("Matricule");

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setCategorieFournisseur(CategorieFournisseur.ORDINAIRE);
        fournisseur.setCode("Code");
        fournisseur.setDetailFournisseur(detailFournisseur);
        fournisseur.setFactures(new HashSet<>());
        fournisseur.setIdFournisseur(1L);
        fournisseur.setLibelle("Libelle");
        fournisseur.setSecteurActivites(new HashSet<>());

        Facture facture = new Facture();
        facture.setArchivee(true);
        facture.setDateCreationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDateDerniereModificationFacture(
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        facture.setDetailsFacture(new HashSet<>());
        facture.setFournisseur(fournisseur);
        facture.setIdFacture(1L);
        facture.setMontantFacture(10.0f);
        facture.setMontantRemise(10.0f);
        facture.setReglements(new HashSet<>());

        Reglement reglement = new Reglement();
        reglement.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement.setFacture(facture);
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(10.0f);
        reglement.setMontantRestant(10.0f);
        reglement.setPayee(true);
        Optional<Reglement> ofResult = Optional.of(reglement);
        when(reglementRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(reglement, reglementServiceImpl.retrieveReglement(1L));
        verify(reglementRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link ReglementServiceImpl#retrieveReglementByFacture(Long)}
     */
    @Test
    void testRetrieveReglementByFacture() {
        ArrayList<Reglement> reglementList = new ArrayList<>();
        when(reglementRepository.retrieveReglementByFacture((Long) any())).thenReturn(reglementList);
        List<Reglement> actualRetrieveReglementByFactureResult = reglementServiceImpl.retrieveReglementByFacture(1L);
        assertSame(reglementList, actualRetrieveReglementByFactureResult);
        assertTrue(actualRetrieveReglementByFactureResult.isEmpty());
        verify(reglementRepository).retrieveReglementByFacture((Long) any());
    }

    /**
     * Method under test: {@link ReglementServiceImpl#getChiffreAffaireEntreDeuxDate(Date, Date)}
     */
    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        when(reglementRepository.getChiffreAffaireEntreDeuxDate((Date) any(), (Date) any())).thenReturn(10.0f);
        Date startDate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        assertEquals(10.0f, reglementServiceImpl.getChiffreAffaireEntreDeuxDate(startDate,
                Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant())));
        verify(reglementRepository).getChiffreAffaireEntreDeuxDate((Date) any(), (Date) any());
    }
}