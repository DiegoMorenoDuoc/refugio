package com.refugio.web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class FamiliaDAO {

	@Autowired
	private IFamiliaDAO crud;
	
	public IFamiliaDAO crud() {
		return this.crud;
	}
	
}
