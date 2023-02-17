package com.example.localizacao.domain.repository;

import com.example.localizacao.domain.entity.Cidade;
import com.example.localizacao.domain.repository.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

    //usamos os nomes das tabelas no banco
    @Query(nativeQuery = true, value = "select * from tb_cidade as c where c.nome = :nome")
    List<Cidade> findByNomeSQLNativo(@Param("nome") String nome);

    @Query(nativeQuery = true, value = "select c.id_cidade as id, c.nome from tb_cidade as c where c.nome = :nome")
    List<CidadeProjection> findByNomeSQLNativoIDeNome(@Param("nome") String nome);

    //vai fazer uma busca pelo atributo nome de Cidade
    List<Cidade> findByNome(String nome);

    //passo a string com % no começo no meio ou no final
    //então eu posso personalizar o meu uso
    //para que eu não diferencia maiuscula de minusculas utilizarey @query
    //e lower() ou upper()
    //Além de fazermos a ordenação aqui
    @Query(" select c from Cidade c where lower(c.nome) like lower(?1)")
    List<Cidade> findByNomeLike(String nome, Sort sort);


    @Query(" select c from Cidade c where lower(c.nome) like lower(?1)")
    Page<Cidade> findByNomeLike(String nome, Pageable pageable);

    //encontrar cidade que comece com aquele pedaço
    List<Cidade> findByNomeStartingWith(String letra);

    //encontrar cidade que termine com aquele pedaço
    List<Cidade> findByNomeEndingWith(String letra);

    //encontrar cidade que tenha aquele pedaço
    List<Cidade> findByNomeContaining(String letra);





    //vai fazer uma busca pelo atributo habitantes de Cidade
    List<Cidade> findByHabitantes(Long habitantes);

    //encontrar cidades com menos de tantos habitante
    List<Cidade> findByHabitantesLessThan(Long habitantes);

    //encontrar cidades com mais de tantos habitantes
    List<Cidade> findByHabitantesGreaterThan(Long habitantes);

    //com menos de tantos habitantes e que tenha a string que eu vou passar
    // de acordo com o %
    List<Cidade> findByHabitantesLessThanAndNomeLike (Long habitantes, String nome);
}
