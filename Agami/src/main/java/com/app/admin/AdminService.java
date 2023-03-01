package com.app.admin;

import com.app.exception.AdminException;
import com.app.exception.UserException;
import com.app.model.Admin;
import com.app.model.CurrentSession;
import com.app.model.LoginDto;
import com.app.model.Routine;
import com.app.model.User;
import java.util.*;

public interface AdminService {
	
	
	public Admin CreateAdmin(Admin admin) throws AdminException;
	
	public CurrentSession login_Admin(LoginDto logindto)throws AdminException;
	// 3 method for admin create staff , view staff ,create routine 
	public User addStaff(User user,String uuid) throws AdminException ,UserException;
	
	public List<User> viewstaff(String uuid)throws UserException,AdminException;
	
	public Routine createRoutin(Routine routine ,String useremail,String uuid) throws UserException ,AdminException;
}
