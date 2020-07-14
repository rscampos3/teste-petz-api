package br.com.testepetz.converter;

import static br.com.testepetz.utils.Validadores.validarNulo;
import static br.com.testepetz.utils.Validadores.validarString;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.testepetz.controller.form.PetShopForm;
import br.com.testepetz.model.Contato;
import br.com.testepetz.model.EnderecoComplementar;
import br.com.testepetz.model.PetShop;

@Component
public class PetShopFormParaModeloConverter implements Converter<PetShopForm, PetShop>{

	@Override
	public PetShop convert(PetShopForm source) {
		Contato contato = new Contato(validarString(source.getTelefone()), validarString(source.getEmail()));
		EnderecoComplementar complementar = new EnderecoComplementar((Long) validarNulo(source.getIdComplementos()),
				validarString(source.getNumero()), validarString(source.getComplemento()),
				validarString(source.getReferencia()));
		
		return PetShop.builder()
				.id((Long) validarNulo(source.getIdPetShop()))
				.cnpj(validarString(source.getCnpj()))
				.razaoSocial(validarString(source.getRazaoSocial()))
				.nomeFantasia(validarString(source.getNomeFantasia()))
				.contato(contato)
				.complemento(complementar)
				.build();
	}

}
