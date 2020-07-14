package br.com.testepetz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.testepetz.model.PetShop;

public interface PetShopRepository extends JpaRepository<PetShop, Long>{
	Page<PetShop> findAllByEndereco_Cep(String cep, Pageable pageable);
}
