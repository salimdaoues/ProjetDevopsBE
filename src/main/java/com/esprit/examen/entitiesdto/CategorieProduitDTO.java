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
public class CategorieProduitDTO implements Serializable {

    private Long idCategorieProduit;
    private String codeCategorie;
    private String libelleCategorie;
}
