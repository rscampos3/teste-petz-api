package br.com.testepetz.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Perfil implements GrantedAuthority {
	private static final long serialVersionUID = -3974271826284083386L;

	@Id
	@GeneratedValue
	private Long id;
	private String nome;

	@Override
	public String getAuthority() {
		return null;
	}
}
