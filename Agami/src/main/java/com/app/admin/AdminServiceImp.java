package com.app.admin;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.dao.AdminDao;
import com.app.dao.CurrentSessionDao;
import com.app.dao.RoutineDao;
import com.app.dao.UserDao;
import com.app.exception.AdminException;
import com.app.exception.UserException;
import com.app.model.Admin;
import com.app.model.CurrentSession;
import com.app.model.LoginDto;
import com.app.model.Routine;
import com.app.model.User;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
	private AdminDao admindao;
	@Autowired
	private CurrentSessionDao currentsessiondao;
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private RoutineDao routinedao;
	
	
	

	public boolean CheckUserIsLogin(String uuid) throws AdminException,UserException{
		CurrentSession currentsession=currentsessiondao.findByUuid(uuid);
		if(currentsession==null) {
			return false;
		}else {
			return true;
		}
	}
	public User findUser(String uuid) throws UserException, AdminException{
		
		if(!CheckUserIsLogin(uuid) ) {
			throw new UserException(" Please Login First ");
		}
		CurrentSession currentsession=currentsessiondao.findByUuid(uuid);
		User user=userdao.findById(currentsession.getUser_id()).get();
		return user;
	}
	
	
	@Override
	public Admin CreateAdmin(Admin admin) throws AdminException {
		// TODO Auto-generated method stub
		
		Admin isAdmin=admindao.findByEmail(admin.getEmail());
		if(isAdmin!=null) {
			throw new AdminException("Admin Is AllReday Register please Login");
		}
		return admindao.save(admin);	
	}

	@Override
	public CurrentSession login_Admin(LoginDto logindto) throws AdminException {
		
		Admin isAdmin=admindao.findByEmail(logindto.getEmail());
		if(isAdmin==null) {
			throw new AdminException("Admin Is Not Register please Register First");
		}
		
		Optional<CurrentSession> loginsession=currentsessiondao.findById(isAdmin.getAdmin_id());
		
		if(loginsession.isPresent()) {
			throw new AdminException("Admin Is All ready Login ");
		}
		if(! isAdmin.getPassword().equals(logindto.getPassword()) ) {
			throw new AdminException(" Please Enter Correct Password");
		}
		CurrentSession currsession=new CurrentSession();
		String uuid=RandomString.make(6);
		currsession.setTimestamp(LocalDateTime.now());
		currsession.setUser_id(isAdmin.getAdmin_id());
		currsession.setUuid(uuid);
		currsession.setActives_status(true);
		
		return currentsessiondao.save(currsession);
	}

	@Override
	public User addStaff(User user,String uuid) throws AdminException, UserException {
		if(!CheckUserIsLogin(uuid)) {
			throw new AdminException("Admin IS Not Login please Login First");
		}
		
		User isuser=userdao.findByEmail(user.getEmail());
		user.setActive(true);
		if(isuser!=null) {
			throw new UserException("This User Is AllReady Login");
		}
		return userdao.save(user);
	}

	@Override
	public List<User> viewstaff(String uuid) throws UserException, AdminException {
		if(!CheckUserIsLogin(uuid)) {
			throw new AdminException("Admin IS Not Login please Login First");
		}
		
		return userdao.findAll();
		
	}

	@Override
	public Routine createRoutin(Routine routine,String useremail, String uuid) throws UserException, AdminException {
		if(!CheckUserIsLogin(uuid)) {
			throw new AdminException("Admin IS Not Login please Login First");
		}
		
		User user=userdao.findByEmail(useremail);
		//create ROutine::--
		if(user==null) {
			throw new UserException("User Is Not vaild ");
		}
		Routine rr=new Routine();
		
		System.out.println(user);
		rr.setUser(user);
		rr.setAttentdance(false);//by deafults beacuse User set attendence:
		rr.setDay(routine.getDay());
		rr.setWork_name(routine.getWork_name());
		rr.setWork_time_duration(routine.getWork_time_duration());
		return routinedao.save(rr);
	}

	

}
