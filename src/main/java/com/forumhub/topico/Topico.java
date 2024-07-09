package com.forumhub.topico;

import com.forumhub.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "autor_id")
    private Long autorId;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DadosCadastrarTopico dado) {
        this.titulo = dado.titulo();
        this.mensagem = dado.mensagem();
        this.dataCriacao = LocalDateTime.now();
        this.autorId = dado.autorId();
        this.curso = dado.curso();
        this.status = Status.NAO_RESPONDIDO;
    }

    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.autorId() != null) {
            this.autorId = dados.autorId();
        }
        if (dados.curso() != null) {
            this.curso = dados.curso();
        }

    }
}
