package com.refugio.web.DAO;

import org.springframework.data.repository.CrudRepository;

import com.refugio.web.entity.Animal;

public interface IAnimalDAO extends CrudRepository<Animal, Integer>{

}
