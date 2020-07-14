package br.com.testepetz.controller.form;

import lombok.Data;

@Data
public class ClienteForm {
	private Long idCliente;
	private String nomeCompleto;
	private String cpf;
	private String rg;
	private String telefone;
	private String email;
	private Long idEndereco;
	private Long idComplementos;
	private String numero;
	private String complemento;
	private String referencia;
}
