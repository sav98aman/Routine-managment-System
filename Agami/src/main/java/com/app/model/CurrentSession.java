package com.app.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CurrentSession {
	
	@Id
	@Column(unique = true)
	private Integer user_id;//admin_id
	private LocalDateTime timestamp;
	private String uuid;
	
	private Boolean actives_status;
}
