package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.app.model.Routine;
import com.app.model.User;

@Repository
public interface RoutineDao extends JpaRepository<Routine, Integer> {
	
	
	public List<Routine> findByUser(User user);
}
