package com.esprit.examen.entitiesdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReglementDTO implements Serializable {
    private Long idReglement;
    private float montantPaye;
    private float montantRestant;
    private Boolean payee;
    @Temporal(TemporalType.DATE)
    private Date dateReglement;
}
