package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //Gera uma tabela no meu banco. JPA. Diz ao JPA que essa classe é uma entidade, ou seja, será mapeada para uma tabela no banco.
@Table(name = "tb_postagens") // Definimos o nome da tabela tb_postagens no meu banco. Seria equivalente ao fazer no MySQL > CREATE TABLE tb_postagens();

public class Postagem {
	
	@Id //Atributo id é a chave primaria da minha tabela. 
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	
	@NotBlank(message = "Atributo titulo é obrigatório!") //Quero que o titulo seja obrigatorio e não pode ter espaços em branco
	@Size(min = 5, max = 100, message = "O atributo título deve ter no mínimo 5 e no máximo 100 caracteres") // Cofigura os caracteres: No minino ter 5 carateres max 100
	@Column(length = 100) // Column faz Configurações da coluna no banco de dados. Length define o tamanho máximo da coluna.
	private String titulo;
	
	
	@NotBlank(message = "Atributo texto é obrigatório!") //Quero que o titulo seja obrigatorio e não pode ter espaços em branco
	@Size(min = 10, max = 1000, message = "O atributo texto deve ter no mínimo 10 e no máximo 1000 caracteres") // Cofigura os caracteres: No minino ter 5 carateres max 100
	@Column(length = 1000) // Column faz Configurações da coluna no banco de dados. Length define o tamanho máximo da coluna.
	private String texto;
	
	@UpdateTimestamp //atualiza data e hora na hora que atualizar e criar
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	

}
