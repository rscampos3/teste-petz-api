package br.com.testepetz.converter;

import static br.com.testepetz.utils.Validadores.validarNulo;
import static br.com.testepetz.utils.Validadores.validarString;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.testepetz.controller.form.ClienteForm;
import br.com.testepetz.model.Cliente;
import br.com.testepetz.model.Contato;
import br.com.testepetz.model.EnderecoComplementar;

@Component
public class ClienteFormParaModeloConverter implements Converter<ClienteForm, Cliente> {

	@Override
	public Cliente convert(ClienteForm source) {

		Cliente cliente = new Cliente();
		Contato contato = new Contato(validarString(source.getTelefone()), validarString(source.getEmail()));
		EnderecoComplementar complementar = new EnderecoComplementar((Long) validarNulo(source.getIdComplementos()),
				validarString(source.getNumero()), validarString(source.getComplemento()),
				validarString(source.getReferencia()));

		cliente.setId((Long) validarNulo(source.getIdCliente()));
		cliente.setNomeCompleto(validarString(source.getNomeCompleto()));
		cliente.setCpf(validarString(source.getCpf()));
		cliente.setRg(validarString(source.getRg()));
		cliente.setContato(contato);
		cliente.setComplemento(complementar);

		return cliente;
	}

}
