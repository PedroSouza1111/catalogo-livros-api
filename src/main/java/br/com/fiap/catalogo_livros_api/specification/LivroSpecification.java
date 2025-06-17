package br.com.fiap.catalogo_livros_api.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.catalogo_livros_api.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroSpecification {

    public static Specification<Livro> comFiltros(String titulo, String autor, Integer dataInicio, Integer dataFim) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filtro por título
            if (titulo != null && !titulo.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("titulo")),
                        "%" + titulo.toLowerCase() + "%"));
            }

            // Filtro por autor
            if (autor != null && !autor.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("autor")),
                        "%" + autor.toLowerCase() + "%"));
            }

            // Filtro por intervalo de data de publicação
            if (dataInicio != null && dataFim != null) {
                predicates.add(criteriaBuilder.between(root.get("anoPublicacao"), dataInicio, dataFim));
            } else if (dataInicio != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("anoPublicacao"), dataInicio));
            } else if (dataFim != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("anoPublicacao"), dataFim));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
