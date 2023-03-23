package com.esprit.examen.services;

import com.esprit.examen.entities.SecteurActivite;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;




import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class SecteurActiviteServiceImplTest {
    @Autowired
    ISecteurActiviteService secteurActiviteService;
    @Test
    public void testSecteur()  {
        SecteurActivite sec = SecteurActivite.builder()
                .codeSecteurActivite("54860")
                .libelleSecteurActivite("testé")
                .build();
        SecteurActivite F2 = secteurActiviteService.addSecteurActivite(sec);
        System.out.print("SecteurActivite " + F2);
        Assertions.assertNotNull(F2.getIdSecteurActivite());
        assertNotNull(F2.getCodeSecteurActivite());
        assertTrue(F2.getLibelleSecteurActivite().length() > 0);
        F2.setCodeSecteurActivite("1111");
        secteurActiviteService.updateSecteurActivite(F2);
        SecteurActivite F3= secteurActiviteService.retrieveSecteurActivite(F2.getIdSecteurActivite());
        assertTrue(F3.getCodeSecteurActivite().equals("1111"));
        secteurActiviteService.deleteSecteurActivite(F2.getIdSecteurActivite());

    }


}
