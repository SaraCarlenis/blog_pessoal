package com.generation.hello_world.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Avisando ao Spring que essa classe é do tipo controller e recebera HTTP
@RequestMapping("/hello") //endereço que ele vai receber a requisição, sempre minuscula
public class HelloController {
	
	
	@GetMapping //preciso indicar que esse método vai receber uma requisição Map
	public String HelloWorld() { //crear o método que vai devolver a String hello world para meu cliente
		return "Hello World";
	}
	
	@GetMapping("/turma")
	public String TurmaJava85() {
		return "Turma 85 da Generation está na quarta semana!</b>";
	}
}

