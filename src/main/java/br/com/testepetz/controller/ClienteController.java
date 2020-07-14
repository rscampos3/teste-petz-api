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

import br.com.testepetz.controller.form.ClienteForm;
import br.com.testepetz.exception.MensagemException;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.model.dto.ClienteDTO;
import br.com.testepetz.service.IClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping
	public ResponseEntity<Page<ClienteDTO>> buscarTodos(Pageable pageable) {
		return new ResponseEntity<Page<ClienteDTO>>(this.clienteService.buscarTodos(pageable), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable("id") Long id) throws MensagemException {
		return new ResponseEntity<ClienteDTO>(this.clienteService.buscarPorId(id), HttpStatus.OK);
	}

	@GetMapping("/buscar-por-cep/{cep}")
	public ResponseEntity<Page<ClienteDTO>> buscarClientesPorCep(@PathVariable("cep") String cep, Pageable pageable)
			throws EnderecoException {
		return new ResponseEntity<Page<ClienteDTO>>(this.clienteService.buscarClientesPorCep(cep, pageable),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> salvar(@Valid @RequestBody ClienteForm clienteForm)
			throws MensagemException, EnderecoException {
		return new ResponseEntity<ClienteDTO>(this.clienteService.cadastrarCliente(clienteForm), HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ClienteDTO> editar(@Valid @RequestBody ClienteForm clienteForm)
			throws MensagemException, EnderecoException {
		return new ResponseEntity<ClienteDTO>(this.clienteService.editarCliente(clienteForm), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable("id") Long id) throws MensagemException {
		this.clienteService.deletarCliente(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
