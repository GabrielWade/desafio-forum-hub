create table topicos
(
    id          bigint       not null auto_increment,
    titulo      varchar(100) not null,
    mensagem    varchar(500) not null,
    dataCriacao datetime     not null,
    autor_id    bigint       not null,
    status      varchar(100) not null,
    primary key (id)
);