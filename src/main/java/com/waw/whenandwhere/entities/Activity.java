package com.waw.whenandwhere.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Activity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false)
	private String Title;
	//add the size
	@Column(nullable = false)
	private String Description;
	//check if its date or date time
	private Date publication_Date;
	private boolean active;
	//private int index;
    private String location;
    //check the video type to register
    private String video;
    private String duration;
    private String period;
    private String language;
    private String openingDays;
    private String schedules;
    private boolean ReservationOnly;
    private int maxSimultaneousReservations;
    private int maxParticipantsReservations;
    private String status;
    private Date validationDate;

   
 
    @OneToMany(mappedBy = "activity")
    private List<Includes> includes;

    @OneToMany(mappedBy = "activity")
    private List<Excludes> excludes;
    
    @OneToMany(mappedBy = "activity")
    private List<Images> images;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    @OneToMany(mappedBy = "activity")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "activity")
    private List<Reviews> reviews;
	
	
}
