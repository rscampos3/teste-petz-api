package br.com.testepetz.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.testepetz.client.BuscaEnderecoClient;
import br.com.testepetz.client.response.EnderecoResponse;
import br.com.testepetz.converter.EnderecoParaEnderecoDTOConverter;
import br.com.testepetz.converter.EnderecoResponseParaEnderecoConverter;
import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.model.Endereco;
import br.com.testepetz.model.dto.EnderecoDTO;
import br.com.testepetz.repository.EnderecoRepository;
import br.com.testepetz.service.IEnderecoService;
import feign.FeignException.FeignClientException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnderecoService implements IEnderecoService {

	@Autowired
	private BuscaEnderecoClient buscaEnderecoClient;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private EnderecoResponseParaEnderecoConverter converterParaModelo;

	@Autowired
	private EnderecoParaEnderecoDTOConverter converterParaDTO;

	@Override
	public EnderecoDTO buscarEnderecoPorCep(String cep) throws EnderecoException{
		try {
			cep = cep.replace("-", "");
			log.debug("Buscando endereço na base");
			Optional<Endereco> enderecoOptional = this.enderecoRepository.findByCep(cep);
			if (enderecoOptional.isEmpty()) {
				log.debug("Buscando o endereço na api");
				Optional<EnderecoResponse> enderecoResponse = this.buscaEnderecoClient.buscarEnderecoPorCep(cep);
				if (enderecoResponse.isPresent()) {
					return this.converterParaDTO
							.convert(this.cadastrarEndereco(converterParaModelo.convert(enderecoResponse.get())));
				}
			} else {
				return this.converterParaDTO.convert(enderecoOptional.get());
			}

		} catch (FeignClientException e) {
			log.error("Erro ao buscar endereço na API status: {0}", e.status());
			throw new EnderecoException("endereco-erro-api");
		}
		return null;
	}

	@Override
	public Endereco cadastrarEndereco(Endereco endereco) throws EnderecoException{
		try {
			log.debug("Cadastrando endereço");
			Optional<Endereco> endOp = Optional.ofNullable(this.enderecoRepository.save(endereco));
			if (endOp.isPresent())
				log.debug("Retornando endereço da base");
			return endOp.get();
		} catch (Exception e) {
			log.error("Erro ao cadastrar endereço");
			throw new EnderecoException("endereco-nao-possivel-cadastrar");
		}
	}

	@Override
	public Endereco buscarEnderecoPorId(Long id) throws EnderecoException {
		log.debug("Buscando endereço por ID: {0}", id);

		Optional<Endereco> endOp = this.enderecoRepository.findById(id);
		if (endOp.isPresent())
			return endOp.get();

		log.error("Endereço não cadastrado ID: {0}", id);
		throw new EnderecoException("endereco-nao-cadastrado");
	}

}
