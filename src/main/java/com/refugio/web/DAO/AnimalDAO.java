package com.refugio.web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AnimalDAO {

	
	@Autowired
	private IAnimalDAO crud;
	
	public IAnimalDAO crud() {
		return this.crud;
	}
	
	
}
