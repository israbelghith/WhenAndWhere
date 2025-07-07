package com.waw.whenandwhere.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    private Integer stars;
    private String text;
    private Date publishDate;
    
    @ManyToOne
    @JoinColumn(name="activity_id")
    private Activity activity;
    
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
}
