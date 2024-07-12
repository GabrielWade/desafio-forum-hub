package com.forumhub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.forumhub.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secrete;

    public String gerarToken(Usuario usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(secrete);
            return JWT.create()
                    .withIssuer("API ForumHub.com")
                    .withSubject(usuario.getEmail())
//                  .withClaim("id", usuario.getId()) podemos passar qulquer informação no token
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token", exception);

        }
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(secrete);
            return JWT.require(algoritmo)
                    .withIssuer("API ForumHub.com")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Erro ao verificar token");
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(100).toInstant(ZoneOffset.of("-03:00"));
    }
}
