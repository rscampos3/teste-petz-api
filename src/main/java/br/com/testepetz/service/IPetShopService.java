package br.com.testepetz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.testepetz.controller.form.PetShopForm;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.exception.MensagemException;
import br.com.testepetz.model.dto.PetShopDTO;

public interface IPetShopService {
	PetShopDTO cadastrarPetShop(PetShopForm petShopForm) throws EnderecoException;

	PetShopDTO editarPetShop(PetShopForm petShopForm) throws EnderecoException, MensagemException;
	
	Page<PetShopDTO> buscarTodos(Pageable pageable);

	PetShopDTO buscarPorId(Long id);

	Page<PetShopDTO> buscarPetShopPorCep(String cep, Pageable pageable);

	void deletarPetShop(Long id) throws MensagemException;
	
	
	
}
