package com.examen.interfaces;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.examen.modelo.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario,Integer>{

	
}