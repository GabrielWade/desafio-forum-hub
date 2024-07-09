package com.forumhub.topico;

import com.forumhub.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.awt.*;
import java.time.LocalDateTime;

public record DadosCadastrarTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotNull
        Long autorId,
        @NotNull
        Curso curso
) {
}
