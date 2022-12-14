package com.viniciusribeiro.springbootmc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viniciusribeiro.springbootmc.domain.Pedido;
import com.viniciusribeiro.springbootmc.repositories.PedidoRepository;
import com.viniciusribeiro.springbootmc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	public Pedido bucar(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! Id: " + id + ",Tipo: " + Pedido.class.getName()));
	}

}
