package br.com.testepetz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testepetz.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Page<Cliente> findAllByEndereco_Cep(String cep, Pageable pageable);
}
