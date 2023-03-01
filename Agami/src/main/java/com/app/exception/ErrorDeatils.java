package com.app.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDeatils {
	
	private LocalDateTime timestamp;
	private String descp;
	private String message;
}
