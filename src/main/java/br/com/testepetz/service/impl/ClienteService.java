package br.com.testepetz.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.testepetz.controller.form.ClienteForm;
import br.com.testepetz.converter.ClienteFormParaModeloConverter;
import br.com.testepetz.converter.ClienteParaClienteDTOConverter;
import br.com.testepetz.exception.MensagemException;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.model.Cliente;
import br.com.testepetz.model.dto.ClienteDTO;
import br.com.testepetz.repository.ClienteRepository;
import br.com.testepetz.service.IClienteService;
import br.com.testepetz.service.IEnderecoService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteService implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private IEnderecoService enderecoService;

	@Autowired
	private ClienteFormParaModeloConverter clienteFormParaModelo;

	@Autowired
	private ClienteParaClienteDTOConverter clienteParaClienteDTOConverter;

	@Override
	public ClienteDTO cadastrarCliente(ClienteForm clienteForm) throws EnderecoException {

		log.debug("Converter form em modelo");
		Cliente cliente = this.clienteFormParaModelo.convert(clienteForm);

		log.debug("Buscar endereço cadastrado");
		cliente.setEndereco(this.enderecoService.buscarEnderecoPorId(clienteForm.getIdEndereco()));

		return this.clienteParaClienteDTOConverter.convert(this.clienteRepository.save(cliente));
	}

	@Override
	public ClienteDTO editarCliente(ClienteForm clienteForm) throws EnderecoException, MensagemException {
		if (clienteForm.getIdCliente() != null && clienteForm.getIdEndereco() != null) {
			log.debug("Converter form em modelo");
			Cliente cliente = this.clienteFormParaModelo.convert(clienteForm);

			log.debug("Buscar endereço cadastrado");
			cliente.setEndereco(this.enderecoService.buscarEnderecoPorId(clienteForm.getIdEndereco()));
			return this.clienteParaClienteDTOConverter.convert(this.clienteRepository.save(cliente));
		}
		log.error("Cliente não cadastrado");
		throw new MensagemException("cliente-nao-cadastrado");
	}

	@Override
	public ClienteDTO buscarPorId(Long id) {
		log.debug("Buscando cliente por ID");
		Optional<Cliente> clienteOptional = this.clienteRepository.findById(id);
		return clienteOptional.isPresent() ? this.clienteParaClienteDTOConverter.convert(clienteOptional.get()) : null;
	}

	@Override
	public Page<ClienteDTO> buscarClientesPorCep(String cep, Pageable pageable) {
		log.debug("Buscando clientes por CEP paginado");
		return this.clienteRepository.findAllByEndereco_Cep(cep, pageable)
				.map(cliente -> this.clienteParaClienteDTOConverter.convert(cliente));
	}

	@Override
	public void deletarCliente(Long id) throws MensagemException {
		try {
			this.clienteRepository.deleteById(id);
		} catch (Exception e) {
			log.debug(e.getMessage());
			throw new MensagemException("cliente-deletar-registro");
		}

	}

	@Override
	public Page<ClienteDTO> buscarTodos(Pageable pageable) {
		log.debug("Buscando todos os cliente");
		return this.clienteRepository.findAll(pageable)
				.map(cliente -> this.clienteParaClienteDTOConverter.convert(cliente));
	}

}
