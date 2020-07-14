package br.com.testepetz.controller.form;

import lombok.Data;

@Data
public class PetShopForm {
	private Long idPetShop;
	private String cnpj;
	private String razaoSocial;
	private String nomeFantasia;
	private String telefone;
	private String email;
	private Long idEndereco;
	private Long idComplementos;
	private String numero;
	private String complemento;
	private String referencia;
}
