package com.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.admin.AdminServiceImp;
import com.app.exception.AdminException;
import com.app.exception.UserException;
import com.app.model.Admin;
import com.app.model.CurrentSession;
import com.app.model.LoginDto;
import com.app.model.Routine;
import com.app.model.User;

@RestController
@RequestMapping(value = "/admin")
@CrossOrigin(origins="*")
public class AdminController {
	
	@Autowired
	private AdminServiceImp adminserviceimpl;
	
	
	@PostMapping(value = "/sigup")
	public ResponseEntity<Admin> create_Amdin_Handaller( @RequestBody Admin admin) throws AdminException{
		Admin newadmin=adminserviceimpl.CreateAdmin(admin);
		
		return new  ResponseEntity<Admin>(newadmin,HttpStatus.CREATED);
	}
	@PostMapping(value = "/login")
	public ResponseEntity<CurrentSession> login_Amdin_Handaller( @RequestBody LoginDto logindto) throws AdminException{
		CurrentSession logindata=adminserviceimpl.login_Admin(logindto);
		return new  ResponseEntity<CurrentSession>(logindata,HttpStatus.OK);
	}
	
	@PostMapping(value = "/addStaff/{uuid}")
	public ResponseEntity<User> AddStaff_Amdin_Handaller( @RequestBody User user ,@PathVariable String uuid) throws AdminException, UserException{
		User newstaffuser=adminserviceimpl.addStaff(user, uuid);
		return new  ResponseEntity<User>(newstaffuser,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/viewstaff/{uuid}")
	public ResponseEntity<List<User>> viewstaff_Amdin_Handaller(@PathVariable String uuid) throws AdminException, UserException{
		List<User> staffList=adminserviceimpl.viewstaff(uuid);
		return new  ResponseEntity<List<User>>(staffList,HttpStatus.OK);
	}
	
	@PostMapping(value = "/createRoutine/{uuid}/{useremail}")
	public ResponseEntity<Routine> createRoutin_Amdin_Handaller(@RequestBody Routine routine ,@PathVariable String uuid, @PathVariable String useremail) throws AdminException, UserException{
		Routine newRoutine=adminserviceimpl.createRoutin(routine,useremail,uuid);
		return new  ResponseEntity<Routine>(newRoutine,HttpStatus.OK);
	}
	
	
}
