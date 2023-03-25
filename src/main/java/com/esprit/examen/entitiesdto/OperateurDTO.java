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
public class OperateurDTO implements Serializable {
    private Long idOperateur;
    private String nom;
    private String prenom;

    private String password;
}
