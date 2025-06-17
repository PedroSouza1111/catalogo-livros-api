package br.com.fiap.catalogo_livros_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título não pode ser nulo ou vazio")
    @Size(min = 3, message = "O título deve ter no mínimo 3 caracteres")
    private String titulo;

    @NotBlank(message = "O nome do autor não pode ser nulo ou vazio")
    private String autor;

    @NotNull(message = "O ano de publicação é obrigatório")
    @Min(value = 1, message = "O ano de publicação deve ser um número válido")
    private Integer anoPublicacao;

    @NotBlank(message = "O ISBN não pode ser nulo ou vazio")
    private String isbn;
}
