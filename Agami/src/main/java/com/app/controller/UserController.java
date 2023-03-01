package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.AdminException;
import com.app.exception.UserException;
import com.app.model.CurrentSession;
import com.app.model.LoginDto;
import com.app.model.Routine;
import com.app.user.UserServiceImpl;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins="*")
public class UserController {
	
	@Autowired
	private UserServiceImpl userserviceimpl;
	
	@PostMapping(value = "/login")
	public ResponseEntity<CurrentSession> UserLogin_handaller(@RequestBody LoginDto logindto) throws UserException{
		
		CurrentSession currentloginsession=userserviceimpl.loginUser(logindto);
		
		return new ResponseEntity<CurrentSession>(currentloginsession,HttpStatus.OK);
	}
	
	@GetMapping(value = "/routine/{uuid}")
	public ResponseEntity<List<Routine>> Routine_view_handaller(@PathVariable String uuid) throws UserException, AdminException{
		
		List<Routine> routinelist=userserviceimpl.viewRoutine(uuid);
		
		return new ResponseEntity<List<Routine>>(routinelist,HttpStatus.OK);
	}
	
	@PatchMapping(value = "/routine/{uuid}/{routine_id}")
	public ResponseEntity<Routine> Routine_Update_handaller(@PathVariable String uuid,@PathVariable Integer routine_id) throws UserException, AdminException{
		
		Routine updateRoutine=userserviceimpl.MarkAttendance(uuid, routine_id);
		
		return new ResponseEntity<Routine>(updateRoutine,HttpStatus.OK);
	}
	
	@PatchMapping(value = "/logout/{uuid}")
	public ResponseEntity<String> Logout_handaller(@PathVariable String uuid) throws UserException, AdminException{
		
		String res=userserviceimpl.Logout(uuid);
		
		return new ResponseEntity<String>(res,HttpStatus.OK);
	}
}
