package br.com.testepetz.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {
	private Long id;
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
}
