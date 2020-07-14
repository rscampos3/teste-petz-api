package br.com.testepetz.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.testepetz.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${petz.jwt.expiration}")
	private String expiration;

	@Value("${petz.jwt.secret}")
	private String secretWord;

	public String gerarToken(Authentication auth) {
		Usuario logado = (Usuario) auth.getPrincipal();

		Date hoje = new Date();
		Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(this.expiration));

		return Jwts.builder().setIssuer("API de teste da PETZ").setSubject(logado.getId().toString()).setIssuedAt(hoje)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, this.secretWord).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secretWord).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secretWord).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}
