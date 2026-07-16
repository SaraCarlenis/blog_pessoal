package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController // Indica que esta classe é um controlador REST. Ou seja, ela vai responder requisições HTTP (GET, POST, PUT, DELETE). e retornar dados geralmente em formato JSON.

@RequestMapping("postagens") // Define o caminho base da URL para acessar os métodos desta classe. Exemplo: http://localhost:8080/postagens

@CrossOrigin(origins = "*", allowedHeaders = "*") // Permite requisições de diferentes origens (CORS). Aqui está liberado para qualquer domínio (*) acessar a API. Útil quando o frontend está em outro servidor.
public class PostagemController {
	
	@Autowired //Injeta o repository automaticamente.Transferencia de responsabilidade.
	private PostagemRepository postagemRepository; //terei acesso a todos os métodos de repository
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll());
	}
}
