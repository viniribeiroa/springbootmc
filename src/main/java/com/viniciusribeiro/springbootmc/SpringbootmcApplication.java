package com.viniciusribeiro.springbootmc;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.viniciusribeiro.springbootmc.domain.Categoria;
import com.viniciusribeiro.springbootmc.repositories.CategoriaRepository;

@SpringBootApplication
public class SpringbootmcApplication implements CommandLineRunner{
	
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootmcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
	}

}
