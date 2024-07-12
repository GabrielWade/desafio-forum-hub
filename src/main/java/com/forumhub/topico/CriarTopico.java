package com.forumhub.topico;

import com.forumhub.usuario.Usuario;
import com.forumhub.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CriarTopico {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public DadosDetalhamentoTopico criar(DadosCadastrarTopico dados) {
        if (dados.autorId() != null && !usuarioRepository.existsById(dados.autorId()))
            throw new IllegalArgumentException("Autor não encontrado");
        Usuario usuario = usuarioRepository.findById(dados.autorId()).get();

        var topico = new Topico(null, dados.titulo(), dados.mensagem(), LocalDateTime.now(), Status.NAO_RESPONDIDO, usuario, dados.curso());
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }

    public DadosDetalhamentoTopico atualizarInformacoes(DadosAtualizacaoTopico dados, Long topicoId) {
        var topico = topicoRepository.getReferenceById(topicoId);
        topico.setTitulo(dados.titulo());
        topico.setMensagem(dados.mensagem());
        topico.setCurso(dados.curso());
        topicoRepository.save(topico);

        return new DadosDetalhamentoTopico(topico);
    }
}
