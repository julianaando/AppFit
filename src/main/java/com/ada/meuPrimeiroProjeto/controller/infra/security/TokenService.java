package com.ada.meuPrimeiroProjeto.controller.infra.security;

import com.ada.meuPrimeiroProjeto.model.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  @Value("${config.token.secret}")
  private String secret;

  public String tokenGenerate(User user){
    try {
      Algorithm algorithm = Algorithm.HMAC512(secret);
      return JWT.create()
        .withSubject(user.getEmail())
        .withClaim("id", user.getId())
        .withClaim("name", user.getName())
        .withExpiresAt(new Date(System.currentTimeMillis() + 3600000))
        .withIssuer("AppFit")
        .sign(algorithm);
    } catch(JWTCreationException e) {
        throw new RuntimeException("Erro ao gerar token", e);
    }
  }

  public String getSubject(String token) {
    try {
      Algorithm algorithm = Algorithm.HMAC512(secret);

      JWTVerifier verifier = JWT.require(algorithm)
          .withIssuer("AppFit")
          .build();

      return verifier.verify(token).getSubject();
    } catch (JWTVerificationException e) {
      throw new RuntimeException("Token inválido", e);
    }
  }

}
