package br.com.testepetz.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.testepetz.controller.form.ClienteForm;
import br.com.testepetz.exception.MensagemException;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.model.dto.ClienteDTO;

public interface IClienteService {
	ClienteDTO cadastrarCliente(ClienteForm clienteForm) throws EnderecoException;

	ClienteDTO editarCliente(ClienteForm clienteForm) throws EnderecoException, MensagemException;
	
	Page<ClienteDTO> buscarTodos(Pageable pageable);

	ClienteDTO buscarPorId(Long id);

	Page<ClienteDTO> buscarClientesPorCep(String cep, Pageable pageable);

	void deletarCliente(Long id) throws MensagemException;
}
