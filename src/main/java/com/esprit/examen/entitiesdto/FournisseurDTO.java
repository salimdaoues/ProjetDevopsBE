package com.esprit.examen.entitiesdto;
import com.esprit.examen.entities.CategorieFournisseur;
import com.esprit.examen.entities.DetailFournisseur;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDTO implements Serializable {
    private Long idFournisseur;
    private String code;
    private String libelle;
    @Enumerated(EnumType.STRING)
    private CategorieFournisseur categorieFournisseur;
    private DetailFournisseur detailFournisseur;
}
