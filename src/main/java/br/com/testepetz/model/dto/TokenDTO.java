package br.com.testepetz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
	private String token;
	private String tipo;
}
