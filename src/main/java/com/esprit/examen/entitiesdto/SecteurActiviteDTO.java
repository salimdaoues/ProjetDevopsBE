package com.esprit.examen.entitiesdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SecteurActiviteDTO implements Serializable {
    private Long idSecteurActivite;
    private String codeSecteurActivite;
    private String libelleSecteurActivite;
}
