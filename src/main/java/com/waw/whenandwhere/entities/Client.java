package com.waw.whenandwhere.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String login;
	private String password;
	private String role;
	
	@OneToMany(mappedBy = "client")
	private List<Reviews> reviews;
	
	@OneToMany(mappedBy = "client")
	private List<Reservation> reservations;
	
	@OneToMany(mappedBy = "client")
	private List<Notification> notifications;
	
    @OneToOne(mappedBy = "client")
    private Template template;
	
}
