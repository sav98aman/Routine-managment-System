package com.app.user;

import java.util.List;

import com.app.exception.AdminException;
import com.app.exception.UserException;
import com.app.model.CurrentSession;
import com.app.model.LoginDto;
import com.app.model.Routine;

public interface UserService {
	
	public CurrentSession loginUser(LoginDto logindto)throws UserException;
	
	public List<Routine> viewRoutine(String uuid) throws UserException, AdminException;
	
	public Routine MarkAttendance(String uuid,Integer routine_id) throws UserException,AdminException;
	
	public String Logout(String uuid) throws UserException,AdminException;
}
