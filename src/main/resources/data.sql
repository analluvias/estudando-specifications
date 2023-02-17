create table tb_cidade(
    id_cidade bigint not null primary key,
    nome varchar(50) not null,
    qtd_habitantes bigint
);

insert into tb_cidade
    (id_cidade, nome, qtd_habitantes)
values
    (1, 'São Paulo', 12396372),
    (2, 'Rio de Janeiro', 1000000),
    (3, 'Fortaleza', 8000000),
    (4, 'Salvador', 5948305843985),
    (5, 'Belo Horizonte', 600000000),
    (6, 'Vitoria', null),
    (7, 'Natal', 2345678),
    (8, 'Joao Pessoa', 25000);