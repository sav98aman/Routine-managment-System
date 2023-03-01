package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.model.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {
	
	@Query("SELECT a FROM Admin a WHERE a.email=?1")
	public Admin findByEmail(String email);
}
