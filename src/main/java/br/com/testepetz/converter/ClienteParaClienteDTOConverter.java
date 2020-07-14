package br.com.testepetz.converter;

import static br.com.testepetz.utils.Validadores.validarString;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.testepetz.model.Cliente;
import br.com.testepetz.model.dto.ClienteDTO;
import br.com.testepetz.model.dto.EnderecoCompletoDTO;

@Component
public class ClienteParaClienteDTOConverter implements Converter<Cliente, ClienteDTO> {

	@Override
	public ClienteDTO convert(Cliente source) {
		EnderecoCompletoDTO end = EnderecoCompletoDTO.builder()
			.cep(validarString(source.getEndereco().getCep()))
			.logradouro(validarString(source.getEndereco().getLogradouro()))
			.bairro(validarString(source.getEndereco().getBairro()))
			.cidade(validarString(source.getEndereco().getCidade()))
			.estado(validarString(source.getEndereco().getEstado()))
			.numero(validarString(source.getComplemento().getNumero()))
			.complemento(validarString(source.getComplemento().getComplemento()))
			.referencia(validarString(source.getComplemento().getReferencia()))
			.build();

		return ClienteDTO.builder()
				.id(source.getId())
				.nomeCompleto(validarString(source.getNomeCompleto()))
				.cpf(validarString(source.getCpf()))
				.rg(validarString(source.getRg()))
				.contato(source.getContato())
				.endereco(end)
				.build();

	}

}
