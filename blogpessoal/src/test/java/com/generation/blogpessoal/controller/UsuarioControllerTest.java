package com.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
import com.generation.blogpessoal.service.UsuarioService;
import com.generation.blogpessoal.util.JwtHelper;
import com.generation.blogpessoal.util.TestBuilder;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UsuarioControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	private static final String BASE_URL = "/usuarios";
	private static final String USUARIO = "root@root.com";
	private static final String SENHA = "rootroot";

	@BeforeAll
	void inicio() {
		usuarioRepository.deleteAll();
		usuarioService.cadastrarUsuario(TestBuilder.criarUsuario(null, "Root", USUARIO, SENHA));
	}

	@Test
	@DisplayName("01 - Deve Cadastrar um novo usuário com sucesso")
	void deveCadastarUsuario() {

		// given
		Usuario usuario = TestBuilder.criarUsuario(null, "Vinicius Lopez", "viniciuslopes@email.com", "vl1585648");

		// when
		// Corpo da Requisição
		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuario);

		// Enviar a Requisição
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);

		// then

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertNotNull(resposta.getBody());

	}

	@Test
	@DisplayName("02 - Não Deve Cadastrar um usuário Duplicado")
	void naoDeveCadastrarUsuarioDuplicado() {

		usuarioService.cadastrarUsuario(
				TestBuilder.criarUsuario(null, "Luiza Guimarães", "luizagui@email.com", "141230678"));

		Usuario usuarioDuplicado = TestBuilder.criarUsuario(null, "Luiza Guimarães", "luizagui@email.com",
				"lugui12345678");

		HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioDuplicado);

		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/cadastrar", HttpMethod.POST,
				corpoRequisicao, Usuario.class);

		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());

	}

	@Test
	@DisplayName("03 - Deve listar todos os usuarios")
	void deveListarTodosUsuarios() {

		// given
		usuarioService.cadastrarUsuario(TestBuilder.criarUsuario(null, "Sara Cortes", "sara@email.com", "SaRa04321"));
		usuarioService
				.cadastrarUsuario(TestBuilder.criarUsuario(null, "Miranda Molina", "mmolina@email.com", "mmlina789650"));
		// when

		String token = JwtHelper.obterToken(testRestTemplate, USUARIO, SENHA);
		// Cabeçalho da Requisição
		HttpEntity<Void> cabecalhoRequisicao = JwtHelper.criarRequisicaoComToken(token);

		// Enviar a Requisição
		ResponseEntity<Usuario[]> resposta = testRestTemplate.exchange(BASE_URL + "/all", HttpMethod.GET,
				cabecalhoRequisicao, Usuario[].class);

		// then

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());

	}

	@Test
	@DisplayName("04 - Deve Atualizar os dados do usuário com sucesso")
	void deveAtualizarUsuario() {
		// Given

		// Objeto para fazer o cadastro
		Usuario usuario = TestBuilder.criarUsuario(null, "Daniel", "daniel@email.com.br", "daniel1234");

		// Fiz o cadastro e guardei os dados objeto
		Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(usuario);

		// Preparar o objeto com a atualização
		Usuario usuarioUpdate = TestBuilder.criarUsuario(usuarioCadastrado.get().getId(), "Daniel Araujo",
				"daniel_araujo@email.com.br", "abcd1234");

		// When

		// Obter o Token
		String token = JwtHelper.obterToken(testRestTemplate, USUARIO, SENHA);

		// Cabeçalho da Requisição
		HttpEntity<Usuario> cabecalhoRequisicao = JwtHelper.criarRequisicaoComToken(usuarioUpdate, token);

		// Enviar a Requisição
		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/atualizar", HttpMethod.PUT,
				cabecalhoRequisicao, Usuario.class);

		// Then

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertNotNull(resposta.getBody());

	}

	@Test
	@DisplayName("05 - Deve Listar um Usuario por ID")
	void deveListarUsuarioPorID() {

		Optional<Usuario> usuarioSalvo = usuarioService.cadastrarUsuario(
				TestBuilder.criarUsuario(null, "Maria Mercedez", "mariamercedez@email.com.br", "1475dSa"));

		Long idBuscado = usuarioSalvo.get().getId();
		String token = JwtHelper.obterToken(testRestTemplate, USUARIO, SENHA);
		
		HttpEntity<Void> cabecalhoRequisicao = JwtHelper.criarRequisicaoComToken(token);

		ResponseEntity<Usuario> resposta = testRestTemplate.exchange(BASE_URL + "/" + idBuscado, HttpMethod.GET,
				cabecalhoRequisicao, Usuario.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals("Maria Joaquina", resposta.getBody().getNome()); // Buscar pelo Body 
	}

}