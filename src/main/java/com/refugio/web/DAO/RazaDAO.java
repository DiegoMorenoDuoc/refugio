package com.refugio.web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RazaDAO {

	
	@Autowired
	private IRazaDAO crud;
	
	public IRazaDAO crud() {
		return this.crud;
	}
	
	
}
