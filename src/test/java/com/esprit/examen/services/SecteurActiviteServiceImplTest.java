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




import static org.junit.Assert.*;

//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class SecteurActiviteServiceImplTest {
    @Autowired
    ISecteurActiviteService secteurActiviteService;
    @Test
    void testSecteur()  {
        SecteurActivite sec = SecteurActivite.builder()
                .codeSecteurActivite("54860")
                .libelleSecteurActivite("testé")
                .build();
        SecteurActivite F2 = secteurActiviteService.addSecteurActivite(sec);
        Assertions.assertNotNull(F2.getIdSecteurActivite());
        assertNotNull(F2.getCodeSecteurActivite());
        assertTrue(F2.getLibelleSecteurActivite().length() > 0);
        F2.setCodeSecteurActivite("1111");
        secteurActiviteService.updateSecteurActivite(F2);
        SecteurActivite F3= secteurActiviteService.retrieveSecteurActivite(F2.getIdSecteurActivite());
        assertSame("1111",F3.getCodeSecteurActivite());
        secteurActiviteService.deleteSecteurActivite(F2.getIdSecteurActivite());

    }


}
