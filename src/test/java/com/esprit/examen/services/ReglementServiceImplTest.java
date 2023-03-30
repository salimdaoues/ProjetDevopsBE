package com.esprit.examen.services;
import com.esprit.examen.entities.*;
import com.esprit.examen.repositories.ReglementRepository;
import lombok.extern.slf4j.Slf4j;
import static org.mockito.Mockito.any;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class ReglementServiceImplTest {
    @Autowired
    IReglementService regService;
    @Autowired
    ReglementRepository  reglementRepository;
    @Test
    void testAddReglement() {
        Reglement reglement1 = new Reglement();
        reglement1.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement1.setIdReglement(1L);
        reglement1.setMontantPaye(10.0f);
        reglement1.setMontantRestant(10.0f);
        reglement1.setPayee(true);
        assertSame(reglement1, regService.addReglement(reglement1));
        regService.retrieveReglement(regService.addReglement(reglement1).getIdReglement());
    }
    @Test

    void testRetrieveReglement() {
        Reglement reglement = new Reglement();
        reglement.setDateReglement(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        reglement.setIdReglement(1L);
        reglement.setMontantPaye(10.0f);
        reglement.setMontantRestant(10.0f);
        reglement.setPayee(true);
        Optional<Reglement> ofResult = Optional.of(reglement);
        when(reglementRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(reglement, regService.retrieveReglement(1L));
        verify(reglementRepository).findById((Long) any());
    }
    @Test
    void testRetrieveReglementByFacture() {
        ArrayList<Reglement> reglementList = new ArrayList<>();
        when(reglementRepository.retrieveReglementByFacture((Long) any())).thenReturn(reglementList);
        List<Reglement> actualRetrieveReglementByFactureResult = regService.retrieveReglementByFacture(1L);
        assertSame(reglementList, actualRetrieveReglementByFactureResult);
        assertTrue(actualRetrieveReglementByFactureResult.isEmpty());
        verify(reglementRepository).retrieveReglementByFacture((Long) any());
    }
}
