package br.com.testepetz.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testepetz.controller.form.PetShopForm;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.exception.MensagemException;
import br.com.testepetz.model.dto.PetShopDTO;
import br.com.testepetz.service.IPetShopService;

@RestController
@RequestMapping("/petshop")
public class PetShopController {
	
	@Autowired
	private IPetShopService petshopService;

	@GetMapping
	public ResponseEntity<Page<PetShopDTO>> buscarTodos(Pageable pageable) {
		return new ResponseEntity<Page<PetShopDTO>>(this.petshopService.buscarTodos(pageable), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PetShopDTO> buscarPorId(@PathVariable("id") Long id) throws MensagemException {
		return new ResponseEntity<PetShopDTO>(this.petshopService.buscarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/buscar-por-cep/{cep}")
	public ResponseEntity<Page<PetShopDTO>> buscarClientesPorCep(@PathVariable("cep") String cep, Pageable pageable)
			throws EnderecoException {
		return new ResponseEntity<Page<PetShopDTO>>(this.petshopService.buscarPetShopPorCep(cep, pageable),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PetShopDTO> salvar(@Valid @RequestBody PetShopForm petShopForm)
			throws MensagemException, EnderecoException {
		return new ResponseEntity<PetShopDTO>(this.petshopService.cadastrarPetShop(petShopForm), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<PetShopDTO> editar(@Valid @RequestBody PetShopForm petShopForm)
			throws MensagemException, EnderecoException {
		return new ResponseEntity<PetShopDTO>(this.petshopService.editarPetShop(petShopForm), HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) throws MensagemException {
		this.petshopService.deletarPetShop(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
