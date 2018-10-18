package com.refugio.web.DAO;

import org.springframework.data.repository.CrudRepository;

import com.refugio.web.entity.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Integer>{
	

}
