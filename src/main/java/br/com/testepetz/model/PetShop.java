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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class PetShop implements Serializable {

	private static final long serialVersionUID = -5587518465941195598L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=14, max=14, message = "cnpj-1")
	@NotBlank(message = "cnpj-2")
	private String cnpj;

	@NotBlank(message = "razao-social")
	private String razaoSocial;

	@NotBlank(message = "nome-fantasia")
	private String nomeFantasia;
	
	@Embedded
	private Contato contato;

	@OneToOne
	@JoinColumn(name = "id_petshop_endereco")
	private Endereco endereco;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_petshop_complemento")
	private EnderecoComplementar complemento;
}
