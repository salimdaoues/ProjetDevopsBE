package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SecteurActiviteServiceImplTest {
    @Autowired
    ISecteurActiviteService secteurActiviteService;


    @Test
    public void testAddSecteur() throws ParseException {
        SecteurActivite sec = SecteurActivite.builder()
                .codeSecteurActivite("54860")
                .libelleSecteurActivite("testé")
                .build();

        SecteurActivite F2 = secteurActiviteService.addSecteurActivite(sec);
        System.out.print("SecteurActivite " + F2);
        assertNotNull(F2.getIdSecteurActivite());
        assertNotNull(F2.getCodeSecteurActivite());
        assertTrue(F2.getLibelleSecteurActivite().length() > 0);
        //secteurActiviteService.deleteSecteurActivite(F2.getIdSecteurActivite());

    }
}
