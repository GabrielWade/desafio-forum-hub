package com.forumhub.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String titulo, String mensagem, LocalDateTime dataCriacao, Status status, Curso curso, Long autorId) {

    public DadosDetalhamentoTopico(Topico topico) {
        this(topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), topico.getStatus(), topico.getCurso(), topico.getAutor().getId());
    }
}
