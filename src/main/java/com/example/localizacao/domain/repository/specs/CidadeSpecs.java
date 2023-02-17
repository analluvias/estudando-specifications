package com.example.localizacao.domain.repository.specs;

import com.example.localizacao.domain.entity.Cidade;
import org.springframework.data.jpa.domain.Specification;

public class CidadeSpecs {

    public static Specification<Cidade> idEqual(Long id){
        //return e ctrl + espaço

        //root representa o path da entidade Cidade, nome (campo a comparar)
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
    }

    public static Specification<Cidade> nomeEqual(String nome){
        //return e ctrl + espaço

        //root representa o path da entidade Cidade, nome (campo a comparar)
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("nome"), nome);
    }

    public static Specification<Cidade> nomeLike(String nome){
        //return e ctrl + espaço

        //root representa o path da entidade Cidade, nome (campo a comparar)
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .like(criteriaBuilder.upper(root.get("nome")), "%"+nome+"%".toUpperCase());
    }

    public static Specification<Cidade> habitantesGreaterThan(Long value){
        //return e ctrl + espaço

        //root representa o path da entidade Cidade, value (campo a comparar)
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .greaterThan(root.get("habitantes"), value);
    }

    public static Specification<Cidade> habitantesBetween(Long min, Long max){
        //return e ctrl + espaço

        //root representa o path da entidade Cidade, nome (campo a comparar)
        return (root, query, criteriaBuilder) -> criteriaBuilder
                .between(root.get("habitantes"), min, max);
    }

}
