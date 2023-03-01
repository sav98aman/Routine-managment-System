package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.app.model.CurrentSession;
import com.app.model.User;

@Repository
public interface CurrentSessionDao extends JpaRepository<CurrentSession, Integer> {
	
	@Query("SELECT a FROM CurrentSession a WHERE a.uuid=?1")
	public CurrentSession findByUuid(String uuid);
}
