package br.com.testepetz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.testepetz.exception.EnderecoException;
import br.com.testepetz.model.dto.EnderecoDTO;
import br.com.testepetz.service.IEnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private IEnderecoService enderecoService;

	@GetMapping("/buscar-por-cep/{cep}")
	public ResponseEntity<EnderecoDTO> buscarEnderecoPorCep(@PathVariable("cep") String cep) throws EnderecoException{
		try {
			return new ResponseEntity<EnderecoDTO>(this.enderecoService.buscarEnderecoPorCep(cep), HttpStatus.OK);
		} catch (Exception e) {
			throw new EnderecoException("Endereço não encontrado");
		}

	}
}
