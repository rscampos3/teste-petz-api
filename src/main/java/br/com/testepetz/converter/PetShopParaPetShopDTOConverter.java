package br.com.testepetz.converter;

import static br.com.testepetz.utils.Validadores.validarString;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.testepetz.model.PetShop;
import br.com.testepetz.model.dto.EnderecoCompletoDTO;
import br.com.testepetz.model.dto.PetShopDTO;

@Component
public class PetShopParaPetShopDTOConverter implements Converter<PetShop, PetShopDTO> {

	@Override
	public PetShopDTO convert(PetShop source) {
		EnderecoCompletoDTO end = EnderecoCompletoDTO.builder().cep(validarString(source.getEndereco().getCep()))
				.logradouro(validarString(source.getEndereco().getLogradouro()))
				.bairro(validarString(source.getEndereco().getBairro()))
				.cidade(validarString(source.getEndereco().getCidade()))
				.estado(validarString(source.getEndereco().getEstado()))
				.numero(validarString(source.getComplemento().getNumero()))
				.complemento(validarString(source.getComplemento().getComplemento()))
				.referencia(validarString(source.getComplemento().getReferencia())).build();

		return PetShopDTO.builder()
				.id(source.getId())
				.cnpj(validarString(source.getCnpj()))
				.razaoSocial(validarString(source.getRazaoSocial()))
				.nomeFantasia(validarString(source.getNomeFantasia()))
				.contato(source.getContato())
				.endereco(end)
				.build();

	}

}
