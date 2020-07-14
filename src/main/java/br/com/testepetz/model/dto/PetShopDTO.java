package br.com.testepetz.model.dto;

import br.com.testepetz.model.Contato;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetShopDTO {
	private Long id;
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	private Contato contato;
	private EnderecoCompletoDTO endereco;
}
