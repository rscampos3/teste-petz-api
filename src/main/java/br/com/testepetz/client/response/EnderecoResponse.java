package br.com.testepetz.client.response;

import lombok.Data;

@Data
public class EnderecoResponse {

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
}
