package br.com.testepetz.service;

import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.model.Endereco;
import br.com.testepetz.model.dto.EnderecoDTO;

public interface IEnderecoService {

	EnderecoDTO buscarEnderecoPorCep(String cep) throws EnderecoException;
	
	Endereco cadastrarEndereco(Endereco endereco) throws EnderecoException;
	
	Endereco buscarEnderecoPorId(Long id) throws EnderecoException;
}
