package com.viniciusribeiro.springbootmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusribeiro.springbootmc.domain.Cliente;
import com.viniciusribeiro.springbootmc.repositories.ClienteRepository;
import com.viniciusribeiro.springbootmc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	public Cliente bucar(Integer id) {
		
		Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ",Tipo: " + Cliente.class.getName()));
	}

}
