package com.generation.hello_world.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Avisando ao Spring que essa classe é do tipo controller e receberá HTTP
@RequestMapping("/hello") // endereço base da requisição
public class HelloController {
	
	
	@GetMapping //preciso indicar que esse método vai receber uma requisição Map
	public String HelloWorld() { // Ao chamar na Web motrara a mensagem > Hello World!
		return "Hello World";
	}
	
	@GetMapping("/turma")
	public String TurmaJava85() {
		return "Turma 85 da Generation está na quarta semana!</b>";
	}
	
	@GetMapping("/bsms")
	public String ListaBsmsGeneration() {
		return ("Lista BSM’s da Generation Brasil: Atenção aos detalhes - Trabalho em Equipe - Comunicação");
	}
	
	@GetMapping("/lista")
	public List<String> listaAprendizaDaSemana() { //Lista utilizando colletions
		return List.of(
				"Banco de Dados",
				"Trabalho em equipe",
				"Spring Boot"
				);
	}
}

