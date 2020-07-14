package br.com.testepetz.converter;

import static br.com.testepetz.utils.Validadores.validarString;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.testepetz.client.response.EnderecoResponse;
import br.com.testepetz.model.Endereco;

@Component
public class EnderecoResponseParaEnderecoConverter implements Converter<EnderecoResponse, Endereco> {

	@Override
	public Endereco convert(EnderecoResponse source) {
		return Endereco.builder()
				.cep(validarString(source.getCep()).replace("-", ""))
				.cidade(validarString(source.getLocalidade()))
				.estado(validarString(source.getUf()))
				.bairro(validarString(source.getBairro()))
				.logradouro(validarString(source.getLogradouro()))
				.build();
	}

}
