package com.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Routine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer routine_id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	private String work_time_duration;
	private String day;
	private String work_name;
	private boolean isAttentdance;
}
