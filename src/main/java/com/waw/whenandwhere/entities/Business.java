package com.waw.whenandwhere.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Business {
	@Id
    private Long id;
    private String userName;
    private String password;
    private String mail;
    private String phone;
    private String description;

    @OneToMany(mappedBy = "business")
    private List<Activity> activities;
    
    @OneToMany(mappedBy = "business")
    private List<Promotion> promotions;
    
}
