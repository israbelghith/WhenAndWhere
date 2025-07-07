package com.waw.whenandwhere.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private double price;
    private String tarifType;
    private boolean isUnique;
    
    @OneToMany(mappedBy = "tarif")
    private List<Reservation> reservations;
    
    @OneToMany(mappedBy = "tarif", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TarifPromo> tarifPromos = new HashSet<>();
}
