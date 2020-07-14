package br.com.testepetz.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.testepetz.controller.form.PetShopForm;
import br.com.testepetz.converter.PetShopFormParaModeloConverter;
import br.com.testepetz.converter.PetShopParaPetShopDTOConverter;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.exception.MensagemException;
import br.com.testepetz.model.PetShop;
import br.com.testepetz.model.dto.PetShopDTO;
import br.com.testepetz.repository.PetShopRepository;
import br.com.testepetz.service.IPetShopService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PetShopService implements IPetShopService {

	@Autowired
	private PetShopRepository petShopRepository;

	@Autowired
	private EnderecoService enderecoService;

	@Autowired
	private PetShopFormParaModeloConverter petShopFormParaModeloConverter;

	@Autowired
	private PetShopParaPetShopDTOConverter petShopParaPetShopDTOConverter;

	@Override
	public PetShopDTO cadastrarPetShop(PetShopForm petShopForm) throws EnderecoException {
		log.debug("Converter form em modelo");
		PetShop petShop = this.petShopFormParaModeloConverter.convert(petShopForm);

		log.debug("Buscar endereço cadastrado");
		petShop.setEndereco(this.enderecoService.buscarEnderecoPorId(petShopForm.getIdEndereco()));

		return this.petShopParaPetShopDTOConverter.convert(this.petShopRepository.save(petShop));
	}

	@Override
	public PetShopDTO editarPetShop(PetShopForm petShopForm) throws EnderecoException, MensagemException {
		if (petShopForm.getIdPetShop() != null && petShopForm.getIdEndereco() != null) {
			log.debug("Converter form em modelo");
			PetShop petShop = this.petShopFormParaModeloConverter.convert(petShopForm);

			log.debug("Buscar endereço cadastrado");
			petShop.setEndereco(this.enderecoService.buscarEnderecoPorId(petShopForm.getIdEndereco()));
			return this.petShopParaPetShopDTOConverter.convert(this.petShopRepository.save(petShop));
		}
		log.error("Cliente não cadastrado");
		throw new MensagemException("petshop-nao-cadastrado");
	}

	@Override
	public Page<PetShopDTO> buscarTodos(Pageable pageable) {
		log.debug("Buscando todos os PetShop cadastrados");
		return this.petShopRepository.findAll(pageable)
				.map(petshop -> this.petShopParaPetShopDTOConverter.convert(petshop));
	}

	@Override
	public PetShopDTO buscarPorId(Long id) {
		log.debug("Buscando PetShop por ID");
		Optional<PetShop> petOp = this.petShopRepository.findById(id);
		return petOp.isPresent() ? this.petShopParaPetShopDTOConverter.convert(petOp.get()) : null;
	}

	@Override
	public Page<PetShopDTO> buscarPetShopPorCep(String cep, Pageable pageable) {
		log.debug("Buscando petshop por CEP paginado");
		return this.petShopRepository.findAllByEndereco_Cep(cep, pageable)
				.map(pet -> this.petShopParaPetShopDTOConverter.convert(pet));
	}

	@Override
	public void deletarPetShop(Long id) throws MensagemException {
		try {
			this.petShopRepository.deleteById(id);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MensagemException("petshop-deletar-registro");
		}

	}

}
