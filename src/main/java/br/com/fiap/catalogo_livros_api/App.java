package br.com.fiap.catalogo_livros_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API Catalogo Livros", version = "v1", description = "API de gestão de um catálogo de livros", contact = @Contact(name = "Pedro Souza", email = "rm555533@fiap.com.br")))
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

}
