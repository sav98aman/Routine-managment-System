package com.app.model;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer user_id;
	private String name;
	private String phone;
	private String email;
	private String password;
	
	private boolean isActive;
	
	@OneToMany(cascade = CascadeType.ALL)
	private java.util.List<Routine> routinelist=new ArrayList<>();
	
}
