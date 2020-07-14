package br.com.testepetz.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoCompletoDTO {
	private String cep;
	private String logradouro;
	private String bairro;
	private String cidade;
	private String estado;
	private String numero;
	private String complemento;
	private String referencia;
}
