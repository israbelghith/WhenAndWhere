package com.waw.whenandwhere.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Images {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column( nullable = false)
	@Lob
    private byte[] image;
	
	@Column( nullable = false)
	private String nameImage;
	
	@Column(nullable = false)
	private String typeImage;
	
	@ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Activity activity;
	
}
