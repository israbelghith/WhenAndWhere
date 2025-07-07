package com.waw.whenandwhere.entities;

import java.util.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TarifPromo {

	@EmbeddedId
    private TarifPromoId id = new TarifPromoId();
	
	@ManyToOne
    @MapsId("idTarif")
    @JoinColumn(name = "tarif_id")
    private Tarif tarif;
	
	@ManyToOne
    @MapsId("idPromotion")
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;
	
	private Date dateApplication;
}
