package com.example.localizacao;

import com.example.localizacao.domain.entity.Cidade;
import com.example.localizacao.domain.repository.CidadeRepository;
import com.example.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

    @Autowired
    private CidadeService service;

    //executado assim que inicializamos o projeto
    @Override
    public void run(String... args) throws Exception {
       service.listarCidadesSQLNATIVO2();
    }


    public static void main(String[] args) {
        SpringApplication.run(LocalizacaoApplication.class, args);
    }


}
