package br.com.testepetz.converter;

import static br.com.testepetz.utils.Validadores.validarString;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.testepetz.model.Endereco;
import br.com.testepetz.model.dto.EnderecoDTO;

@Component
public class EnderecoParaEnderecoDTOConverter implements Converter<Endereco, EnderecoDTO>{

	@Override
	public EnderecoDTO convert(Endereco source) {
		return EnderecoDTO.builder()
				.id(source.getId())
				.cep(validarString(source.getCep()).replace("-", ""))
				.cidade(validarString(source.getCidade()))
				.estado(validarString(source.getEstado()))
				.bairro(validarString(source.getBairro()))
				.logradouro(validarString(source.getLogradouro()))
				.build();
	}


}
