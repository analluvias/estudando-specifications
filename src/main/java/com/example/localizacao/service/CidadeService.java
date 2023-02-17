package com.example.localizacao.service;

import com.example.localizacao.domain.entity.Cidade;
import com.example.localizacao.domain.repository.CidadeRepository;
import com.example.localizacao.domain.repository.specs.CidadeSpecs;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository repository){

        this.cidadeRepository = repository;
    }


    public void listarCidades(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    public void listarCidadesPorQuantHabitantes(){
        cidadeRepository.findByHabitantesLessThanAndNomeLike(1000000l, "J%")
                .forEach(System.out::println);
    }

    @Transactional
    public void salvarCidade(){
        var cidade = new Cidade(1L, "São Paulo", 1239672L);
        cidadeRepository.save(cidade);
    }

    public void listarCidadePorNome(){
        cidadeRepository.findByNomeLike("%a%", Sort.by("habitantes")).forEach(System.out::println);
    }

    public void listarCidadePorHabitantes(){
        cidadeRepository.findByHabitantes(5948305843985L).forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){

        Example<Cidade> example = Example.of(cidade);
        return cidadeRepository.findAll(example);
    }

    public void listarCidadesSQLNATIVO(){
        cidadeRepository
                .findByNomeSQLNativo("São Paulo")
                .forEach(System.out::println);
    }
    public void listarCidadesSQLNATIVO2(){
        cidadeRepository
                .findByNomeSQLNativoIDeNome("São Paulo")
                .stream().map(cidadeProjection ->
                        new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }

    public void listarCidadesByNomeSpec(){
        Specification<Cidade> nomeIgualSP = CidadeSpecs.nomeEqual("São Paulo").
                and(CidadeSpecs.habitantesGreaterThan(100000L));

        cidadeRepository.findAll(nomeIgualSP).forEach(System.out::println);
    }

    public void listarCidadesSpecsFiltroDinamico(Cidade filtro){

        //criando uma specification para cidade:
        //select * from cidade where 1 = 1
        Specification<Cidade> specs = Specification.where(
                ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction()));

        //se eu passei o campo id, a minha spec vai adicionar mais uma clausula
        if (filtro.getId() != null){
            specs = specs.and( CidadeSpecs.idEqual(filtro.getId()) );
        }

        //se eu passei nome, então tabém vamos adicionar clausula procurar por nome
        if (StringUtils.hasText(filtro.getNome())){
            specs = specs.and(CidadeSpecs.nomeLike(filtro.getNome()));
        }


        //se eu passei o numero de habitantes, então vamos adicionar clausula do numero de habitantes
        if (filtro.getHabitantes() != null){
            specs = specs.and(CidadeSpecs.habitantesGreaterThan(filtro.getHabitantes()));
        }

        cidadeRepository.findAll(specs).forEach(System.out::println);
    }
}
