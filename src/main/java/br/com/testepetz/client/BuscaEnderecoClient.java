package br.com.testepetz.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.testepetz.client.response.EnderecoResponse;

@FeignClient(name = "BuscaCep", url = "${endpoint.externo.viacep.buscacep}")
public interface BuscaEnderecoClient {

	@RequestMapping(method = RequestMethod.GET, value = "/{cep}/json")
	Optional<EnderecoResponse> buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
