package com.waw.whenandwhere.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TarifPromoId implements Serializable {

	private Long idTarif;
    private Long idPromotion;
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TarifPromoId that = (TarifPromoId) o;
        return Objects.equals(idTarif, that.idTarif) &&
               Objects.equals(idPromotion, that.idPromotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTarif, idPromotion);
    }
}
