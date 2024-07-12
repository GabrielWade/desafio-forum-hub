alter table topicos
    add constraint fk_topicos_autor_id
        foreign key (autor_id) references usuarios(id);