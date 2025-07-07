package com.waw.whenandwhere.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Promotion {
	    @Id
	    private Long id;
	    
	    private String promotionName;
	    private Double discountPercentage;
	    private Date startDate;
	    private Date endDate;
	    
	    @ManyToOne
	    @JoinColumn(name = "business_id")
	    private Business business;
	    
	    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
	    private Set<TarifPromo> tarifPromos = new HashSet<>();
}
