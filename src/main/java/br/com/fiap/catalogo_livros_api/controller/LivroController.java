package br.com.fiap.catalogo_livros_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.catalogo_livros_api.dto.LivroDto;
import br.com.fiap.catalogo_livros_api.model.Livro;
import br.com.fiap.catalogo_livros_api.repository.LivroRepository;
import br.com.fiap.catalogo_livros_api.specification.LivroSpecification;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid LivroDto livroDto) {
        Livro novoLivro = new Livro();

        novoLivro.setTitulo(livroDto.getTitulo());
        novoLivro.setAutor(livroDto.getAutor());
        novoLivro.setAnoPublicacao(livroDto.getAnoPublicacao());
        novoLivro.setIsbn(livroDto.getIsbn());

        Livro livroSalvo = livroRepository.save(novoLivro);

        return ResponseEntity.status(HttpStatus.CREATED).body(livroSalvo);
    }

    @GetMapping
    public Page<Livro> listarLivros(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String autor,
            @RequestParam(required = false) Integer dataInicio,
            @RequestParam(required = false) Integer dataFim,
            Pageable pageable) {

        Specification<Livro> spec = LivroSpecification.comFiltros(titulo, autor, dataInicio, dataFim);

        return livroRepository.findAll(spec, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> obterLivroPorId(@PathVariable Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
        return ResponseEntity.ok(livro);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable Long id,
            @RequestBody @Valid LivroDto detalhesLivroDto) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(detalhesLivroDto.getTitulo());
                    livro.setAutor(detalhesLivroDto.getAutor());
                    livro.setAnoPublicacao(detalhesLivroDto.getAnoPublicacao());
                    livro.setIsbn(detalhesLivroDto.getIsbn());

                    Livro livroAtualizado = livroRepository.save(livro);
                    return ResponseEntity.ok(livroAtualizado);
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarLivro(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }
}
