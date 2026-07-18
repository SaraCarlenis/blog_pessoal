package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.blogpessoal.model.Postagem;

public interface PostagemRepository extends JpaRepository<Postagem, Long>{  
	// Fazemos herança chamando Postagem e Long, 
    // onde Postagem é a entidade e Long é o tipo da chave primária (ID).
	
	public List<Postagem> findAllByTituloContainingIgnoreCase(String titulo); //E como falar: SELECT * FROM tb_postagens WHERE titulo LIKE "%?%"
		
}
