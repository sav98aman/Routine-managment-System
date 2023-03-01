package com.app.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.admin.AdminServiceImp;
import com.app.dao.CurrentSessionDao;
import com.app.dao.RoutineDao;
import com.app.dao.UserDao;
import com.app.exception.AdminException;
import com.app.exception.UserException;
import com.app.model.CurrentSession;
import com.app.model.LoginDto;
import com.app.model.Routine;
import com.app.model.User;

import net.bytebuddy.utility.RandomString;

@Repository
public class UserServiceImpl implements UserService {
	
	@Autowired
	private CurrentSessionDao currentsessiondao;
	
	@Autowired
	private RoutineDao routinedao;
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private AdminServiceImp adminserviceimpl;
	@Override
	public CurrentSession loginUser(LoginDto logindto) throws UserException {
		// TODO Auto-generated method stub
		User ISUser= userdao.findByEmail(logindto.getEmail());
		if (ISUser == null) {
			throw new UserException("User Is Not Register ");
		}
		
		Optional<CurrentSession> optsession= currentsessiondao.findById(ISUser.getUser_id());
		
		if(optsession.isPresent()) {
			throw new UserException("User Is AlReady Login");
		}
		
		//check password::
		if( !ISUser.getPassword().equals(logindto.getPassword())){
			throw new UserException(" Please Enter Correct Password ");
		}
		String uuid=RandomString.make(6);
		CurrentSession session =new CurrentSession();
		session.setActives_status(true);
		session.setTimestamp(LocalDateTime.now());
		session.setUser_id(ISUser.getUser_id());
		session.setUuid(uuid);
		
		return currentsessiondao.save(session);
	}

	@Override
	public List<Routine> viewRoutine(String uuid) throws UserException, AdminException {
		
		if(! adminserviceimpl.CheckUserIsLogin(uuid)) {
			throw new UserException(" Bad Credintials " );
		}
		User user =adminserviceimpl.findUser(uuid);
		return routinedao.findByUser(user);
	}

	@Override
	public Routine MarkAttendance(String uuid, Integer routine_id) throws UserException, AdminException {
		
		if(!adminserviceimpl.CheckUserIsLogin(uuid)) {
			throw new UserException(" Bad Credintials " );
		}
		Routine rr=routinedao.findById(routine_id).get();
		rr.setAttentdance(true);
		return routinedao.save(rr);
		
	}

	@Override
	public String Logout(String uuid) throws UserException, AdminException {
		if(!adminserviceimpl.CheckUserIsLogin(uuid)) {
			throw new UserException(" Bad Credintials " );
		}
		CurrentSession curr=currentsessiondao.findByUuid(uuid);
		currentsessiondao.delete(curr);
		return "logut succesfully!";
	}

}
