package br.com.testepetz.model.dto;

import br.com.testepetz.model.Contato;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
	private Long id;
	private String nomeCompleto;
	private String cpf;
	private String rg;
	private Contato contato;
	private EnderecoCompletoDTO endereco;
}
