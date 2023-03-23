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
public class StockDTO implements Serializable {
    private Long idStock;
    private String libelleStock;
    private Integer qte;
    private Integer qteMin;
}
