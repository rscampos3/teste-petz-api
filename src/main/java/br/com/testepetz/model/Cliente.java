package br.com.testepetz.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 3836770984462482606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "nome-completo")
	private String nomeCompleto;

	@Size(min = 11, max = 11, message = "cpf-1")
	@NotBlank(message = "cpf-2")
	private String cpf;

	@NotBlank(message = "rg")
	private String rg;

	@Embedded
	private Contato contato;

	@OneToOne
	@JoinColumn(name = "id_cliente_endereco")
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente_complemento")
	private EnderecoComplementar complemento;
}
