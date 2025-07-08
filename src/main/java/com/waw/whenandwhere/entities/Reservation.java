package com.waw.whenandwhere.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private Date startTime;
    private Date endTime;
    private double price;
    private String resourceName;
    private int totalResourcePerHour;
    private int maxPeoplePerResource;
    private Date timeSlots;
    private int totalParticipants;
    private int duration;
    private String type;

    
    
    @OneToOne(mappedBy = "reservation")
    private Payement payement;
    
    @OneToMany(mappedBy = "reservation")
    private List<Individual> individuals;
    
    @OneToMany(mappedBy = "reservation")
    private List<Pet> pets;
    
    @OneToMany(mappedBy = "reservation")
    private List<Export> exports;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    
    @ManyToOne
	@JoinColumn(name="unit_id" ,nullable = true)
	private Unit unit;
    
	@ManyToOne
	@JoinColumn(name="activity_id")
	private Activity activity;
	
    @ManyToOne
    @JoinColumn(name = "tarif_id")
    private Tarif tarif;
}
