package com.viniciusribeiro.springbootmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusribeiro.springbootmc.domain.Categoria;
import com.viniciusribeiro.springbootmc.repositories.CategoriaRepository;
import com.viniciusribeiro.springbootmc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria bucar(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ",Tipo: " + Categoria.class.getName()));
	}

}
